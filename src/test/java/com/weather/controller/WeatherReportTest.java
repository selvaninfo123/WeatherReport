package com.weather.controller;

import com.weather.bo.WeatherRequestsBO;
import com.weather.model.WeatherApiRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WeatherReportTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    public void testWeatherReport() {
        WeatherApiRequest weatherApiRequest = new WeatherApiRequest();
        weatherApiRequest.setPostalCode("33601");
        weatherApiRequest.setUser(1L);

        ResponseEntity<WeatherRequestsBO> response = restTemplate.postForEntity(
                "/api/weather",
                weatherApiRequest,
                WeatherRequestsBO.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("33601", response.getBody().getPostalCode());

        System.out.println("Response: " + response.getBody());
    }

    @Test
    @Order(2)
    public void testWeatherHistory() {

        ResponseEntity<WeatherRequestsBO[]> response = restTemplate.getForEntity(
                "/api/weather/history?user=1", WeatherRequestsBO[].class
        );


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("33601", response.getBody()[0].getPostalCode());

        System.out.println("Response: " + Arrays.toString(response.getBody()));
    }
}
