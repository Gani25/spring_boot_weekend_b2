package com.sprk.api_call.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Forecastday{
    private String date;
    private int date_epoch;
    private Day day;
    private Astro astro;
    private ArrayList<Hour> hour;
}