package com.weather.controller;

import com.weather.bo.WeatherRequestsBO;
import com.weather.model.WeatherApiRequest;
import com.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping(value = "/weather", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeatherRequestsBO> fetchWeather(
            @RequestBody WeatherApiRequest weatherApiRequest) {
        WeatherRequestsBO res = weatherService.fetchWeatherData(weatherApiRequest);
        return ResponseEntity.ok(res);
    }

    @GetMapping(value = "/weather/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WeatherRequestsBO>> getHistory(WeatherApiRequest weatherApiRequest) {
        return ResponseEntity.ok(weatherService.getHistory(weatherApiRequest));
    }
}
