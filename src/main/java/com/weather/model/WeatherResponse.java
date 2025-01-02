package com.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class WeatherResponse {
    @JsonProperty("current_weather")
    private CurrentWeather currentWeather;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class CurrentWeather {
        private double temperature;
        private double windspeed;
        private double humidity;
    }
}

