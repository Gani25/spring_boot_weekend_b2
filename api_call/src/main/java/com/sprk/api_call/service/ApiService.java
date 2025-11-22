package com.sprk.api_call.service;

import com.sprk.api_call.dto.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Value(value = "${weather.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public Root getApiResponse(String query, int forecastDays){

        String url = "https://api.weatherapi.com/v1/forecast.json?q="+query+"&days="+forecastDays+"&key="+apiKey;

        Root root = restTemplate.getForObject(url, Root.class);

        root.getLocation().getCountry();

        return root;

    }


}
