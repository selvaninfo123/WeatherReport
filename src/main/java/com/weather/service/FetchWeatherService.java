package com.weather.service;

import com.weather.model.WeatherResponse;

public interface FetchWeatherService {

    WeatherResponse getWeatherByPostalCode(String postalCode, String country);
}
