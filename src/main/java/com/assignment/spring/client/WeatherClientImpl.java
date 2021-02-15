package com.assignment.spring.client;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assignment.spring.client.model.WeatherResponse;
import com.assignment.spring.domain.Weather;
import com.assignment.spring.mapper.WeatherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
class WeatherClientImpl implements WeatherClient {

	private final RestTemplate restTemplate;

	private final WeatherMapper weatherMapper;

	@Value("${weather.api.app.id}")
	private String appId;

	@Value("${weather.api.url}")
	private String weatherApiUrl;

	@Override
	public Optional<Weather> getWeather(String city) {
		log.debug("Loading weather for city : [{}]", city);
		if (StringUtils.isBlank(city)) {
			throw new IllegalArgumentException("city cannot be empty");
		}

		String url = weatherApiUrl.replace("{city}", city).replace("{appid}", appId);
		ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
		Weather weather = weatherMapper.map(response.getBody());
		log.info("Loading weather finished with result : [{}]", weather);
		return Optional.ofNullable(weather);
	}
}
