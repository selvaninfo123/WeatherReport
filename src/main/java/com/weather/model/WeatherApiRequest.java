package com.weather.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Setter
@Getter
public class WeatherApiRequest {

    private String postalCode;
    private Long user;
}
