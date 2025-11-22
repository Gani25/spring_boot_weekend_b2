package com.sprk.api_call.dto;

import lombok.Data;

@Data
public class Root{
    private Location location;
    private Current current;
    private Forecast forecast;
}