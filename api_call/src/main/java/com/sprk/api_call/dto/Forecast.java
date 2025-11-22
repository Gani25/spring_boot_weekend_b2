package com.sprk.api_call.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Forecast{
    private ArrayList<Forecastday> forecastday;
}