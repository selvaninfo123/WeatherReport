package com.weather.bo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class WeatherRequestsBO {

    private Long id;
    private String userName;
    private String postalCode;
    private Float temperature;
    private Float humidity;
    private String weatherConditions;
    private LocalDateTime requestTime;
}