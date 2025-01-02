package com.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class NominatimResponse {
    @JsonProperty("lat")
    private String latitude;

    @JsonProperty("lon")
    private String longitude;
}
