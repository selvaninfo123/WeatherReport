package com.weather.service.impl;

import com.weather.model.NominatimResponse;
import com.weather.model.WeatherResponse;
import com.weather.service.FetchWeatherService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class FetchWeatherServiceImpl implements FetchWeatherService {

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeatherByPostalCode(String postalCode, String country) {
        // Step 1: Get latitude and longitude from Nominatim
        String nominatimUrl = UriComponentsBuilder
                .fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("postalcode", postalCode)
                .queryParam("country", country)
                .queryParam("format", "json")
                .toUriString();

        NominatimResponse[] nominatimResponses = restTemplate.getForObject(nominatimUrl, NominatimResponse[].class);

        if (nominatimResponses == null || nominatimResponses.length == 0) {
            throw new IllegalArgumentException("Invalid postal code or no data found.");
        }

        NominatimResponse location = nominatimResponses[0];

        // Step 2: Fetch weather data from Open-Meteo
        String weatherUrl = UriComponentsBuilder
                .fromHttpUrl("https://api.open-meteo.com/v1/forecast")
                .queryParam("latitude", location.getLatitude())
                .queryParam("longitude", location.getLongitude())
                .queryParam("current_weather", "true")
                .toUriString();

        return restTemplate.getForObject(weatherUrl, WeatherResponse.class);
    }
}
