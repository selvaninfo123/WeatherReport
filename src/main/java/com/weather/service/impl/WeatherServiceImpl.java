package com.weather.service.impl;

import com.weather.bo.WeatherRequestsBO;
import com.weather.entity.Users;
import com.weather.entity.WeatherRequests;
import com.weather.model.WeatherApiRequest;
import com.weather.model.WeatherResponse;
import com.weather.repository.UserRepository;
import com.weather.repository.WeatherRequestRepository;
import com.weather.service.FetchWeatherService;
import com.weather.service.WeatherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRequestRepository weatherRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FetchWeatherService fetchWeatherService;

    public WeatherRequestsBO fetchWeatherData(WeatherApiRequest weatherApiRequest) {
        Users user = userRepository.findById(weatherApiRequest.getUser())
                .orElseThrow(() -> new IllegalArgumentException("Invalid User"));

        // Mocked API response (Replace with actual HTTP call)
        Float temperature = 72.5F;
        Float humidity = 60.0F;
        String weatherConditions = "Clear";

        WeatherResponse weatherResponse = fetchWeatherService.getWeatherByPostalCode(weatherApiRequest.getPostalCode(), "us");

        WeatherRequests request = new WeatherRequests();
        request.setUser(user);
        request.setPostalCode(weatherApiRequest.getPostalCode());
        request.setTemperature(weatherResponse.getCurrentWeather().getTemperature());
        request.setHumidity(weatherResponse.getCurrentWeather().getHumidity());
        request.setWeatherConditions(String.valueOf(weatherResponse.getCurrentWeather().getWindspeed()));

        weatherRequestRepository.save(request);
        WeatherRequestsBO response = new WeatherRequestsBO();
        BeanUtils.copyProperties(request, response);
        return response;
    }

    @Override
    public List<WeatherRequestsBO> getHistory(WeatherApiRequest weatherApiRequest) {
        if (weatherApiRequest.getPostalCode() != null) {
            return getWeatherRequestsBOs(weatherRequestRepository.findByPostalCode(weatherApiRequest.getPostalCode()));
        } else if (weatherApiRequest.getUser() != null) {
            Users user = userRepository.findById(weatherApiRequest.getUser())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid User"));
            return getWeatherRequestsBOs(weatherRequestRepository.findByUser(user));
        } else {
            return List.of();
        }
    }

    private List<WeatherRequestsBO> getWeatherRequestsBOs(List<WeatherRequests> requests) {
        return Optional.ofNullable(requests).orElse(List.of()).stream().map(request -> {
            WeatherRequestsBO bo = new WeatherRequestsBO();
            BeanUtils.copyProperties(request, bo);
            return bo;
        }).collect(Collectors.toList());
    }
}
