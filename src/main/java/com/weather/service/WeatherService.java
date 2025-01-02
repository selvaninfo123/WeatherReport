package com.weather.service;

import com.weather.bo.WeatherRequestsBO;
import com.weather.entity.WeatherRequests;
import com.weather.model.WeatherApiRequest;

import java.util.List;

public interface WeatherService {

    WeatherRequestsBO fetchWeatherData(WeatherApiRequest weatherApiRequest);
    List<WeatherRequestsBO> getHistory(WeatherApiRequest weatherApiRequest);
}
