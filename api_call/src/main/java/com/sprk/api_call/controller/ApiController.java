package com.sprk.api_call.controller;

import com.sprk.api_call.dto.Root;
import com.sprk.api_call.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/weatherinfo")
    public Root getWeatherInfo(@RequestParam(defaultValue = "Mumbai", required = false) String location, @RequestParam(required = false, defaultValue = "1") Integer forecastDays) {

        return apiService.getApiResponse(location, forecastDays);

    }
}
