package com.weather.bo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsersBO {

    private Long id;
    private String name;
    private Boolean active;
}