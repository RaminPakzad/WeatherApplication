package com.assignment.spring.service;

import org.springframework.stereotype.Service;

import com.assignment.spring.client.WeatherClient;
import com.assignment.spring.domain.Weather;
import com.assignment.spring.dto.WeatherDTO;
import com.assignment.spring.exception.NotFoundException;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
class WeatherServiceImpl implements WeatherService {

	private final WeatherClient weatherClient;

	private final WeatherRepository weatherRepository;

	private final WeatherMapper weatherMapper;

	@Override
	public WeatherDTO processWeather(String city) {
		log.info("Processing weather for city : [{}]", city);
		return weatherClient.getWeather(city)
				.map(this::save)
				.map(weatherMapper::map)
				.orElseThrow(() -> new NotFoundException(String.format("Could not find weather for city: [%s]", city)));
	}

	private Weather save(Weather weather) {
		log.debug("Saving weather: [{}]", weather);
		return weatherRepository.save(weather);
	}

}
