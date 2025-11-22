package com.sprk.api_call.dto;

import lombok.Data;

@Data
public class Hour {
    private int time_epoch;
    private String time;
    private double temp_c;
    private double temp_f;
    private int is_day;
    private Condition condition;
    private double wind_mph;
    private double wind_kph;
    private int wind_degree;
    private String wind_dir;
    private int pressure_mb;
    private double pressure_in;
    private int precip_mm;
    private int precip_in;
    private int snow_cm;
    private int humidity;
    private int cloud;
    private double feelslike_c;
    private double feelslike_f;
    private double windchill_c;
    private double windchill_f;
    private double heatindex_c;
    private double heatindex_f;
    private double dewpoint_c;
    private double dewpoint_f;
    private int will_it_rain;
    private int chance_of_rain;
    private int will_it_snow;
    private int chance_of_snow;
    private int vis_km;
    private int vis_miles;
    private double gust_mph;
    private double gust_kph;
    private double uv;
}