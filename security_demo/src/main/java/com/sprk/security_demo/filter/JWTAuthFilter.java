package com.sprk.security_demo.filter;

import com.sprk.security_demo.service.CustomUserDetailService;
import com.sprk.security_demo.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI(); // this will give api path after /home
        // do not check public request
        if (path.contains("/generate-token") || path.equals("/users") || path.equals("/signup") || path.equals("/home")) {
            System.out.println("No Filtering");
            filterChain.doFilter(request, response);// move ahead without filtering
            return;
        }
        try {
            String username = null;
            String token = null;
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null || authHeader.startsWith("Bearer ")) {
                // extract token
                token = authHeader.substring("Bearer ".length());
                username = jwtService.extractUsername(token);
            }

            // I only want to validate token for the first time
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Expired JWT token\"}");

        } catch (JwtException e) {
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Invalid token\"}");


        } catch (Exception e) {

            response.setContentType("application/json");
            response.getWriter().write("{\"error\":" + e.getMessage() + "}");
        }

    }
}
