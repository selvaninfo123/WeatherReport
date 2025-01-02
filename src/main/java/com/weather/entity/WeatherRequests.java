package com.weather.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@Entity
public class WeatherRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;

    private String postalCode;
    private Double temperature;
    private Double humidity;
    private String weatherConditions;

    @Column(updatable = false)
    private LocalDateTime requestTime = LocalDateTime.now();
}
