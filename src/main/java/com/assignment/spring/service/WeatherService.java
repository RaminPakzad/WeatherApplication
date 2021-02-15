package com.assignment.spring.service;

import com.assignment.spring.dto.WeatherDTO;

public interface WeatherService {
	WeatherDTO processWeather(String city);
}
