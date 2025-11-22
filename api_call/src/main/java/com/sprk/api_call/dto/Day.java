package com.sprk.api_call.dto;

import lombok.Data;

@Data
public class Day {
    private double maxtemp_c;
    private double maxtemp_f;
    private double mintemp_c;
    private double mintemp_f;
    private double avgtemp_c;
    private double avgtemp_f;
    private double maxwind_mph;
    private double maxwind_kph;
    private int totalprecip_mm;
    private int totalprecip_in;
    private int totalsnow_cm;
    private int avgvis_km;
    private int avgvis_miles;
    private int avghumidity;
    private int daily_will_it_rain;
    private int daily_chance_of_rain;
    private int daily_will_it_snow;
    private int daily_chance_of_snow;
    private Condition condition;
    private double uv;
}
