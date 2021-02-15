package com.assignment.spring.client;


import java.util.Optional;

import com.assignment.spring.domain.Weather;

public interface WeatherClient {
	Optional<Weather> getWeather(String city);
}
