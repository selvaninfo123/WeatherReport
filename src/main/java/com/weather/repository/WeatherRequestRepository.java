package com.weather.repository;

import com.weather.entity.Users;
import com.weather.entity.WeatherRequests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRequestRepository extends JpaRepository<WeatherRequests, Long> {
    List<WeatherRequests> findByPostalCode(String postalCode);
    List<WeatherRequests> findByUser(Users user);
}
