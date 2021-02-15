package com.assignment.spring.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import com.assignment.spring.client.model.MainWeather;
import com.assignment.spring.client.model.Sys;
import com.assignment.spring.client.model.WeatherResponse;
import com.assignment.spring.domain.Weather;
import com.assignment.spring.dto.WeatherDTO;


class WeatherMapperTest {

	private static final Integer ID = 1;

	private static final String CITY = "Amsterdam";

	private static final String COUNTRY = "NL";

	private static final Double TEMPERATURE = 20.1;

	private final WeatherMapper weatherMapper = new WeatherMapperImpl();

	@Test
	void mapWeatherToWeatherDTO() {
		WeatherDTO dto = weatherMapper.map(getWeather());

		assertThat(dto, allOf(
				hasProperty("id", is(ID)),
				hasProperty("city", is(CITY)),
				hasProperty("country", is(COUNTRY)),
				hasProperty("temperature", is(TEMPERATURE))
		));
	}

	@Test
	void mapWeatherResponseToWeather() {
		WeatherResponse weatherResponse = getWeatherResponse();
		Weather weather = weatherMapper.map(weatherResponse);

		assertThat(weather, allOf(
				hasProperty("id", is(ID)),
				hasProperty("city", is(CITY)),
				hasProperty("country", is(COUNTRY)),
				hasProperty("temperature", is(TEMPERATURE))
		));
	}

	private WeatherResponse getWeatherResponse() {
		WeatherResponse weatherResponse = new WeatherResponse();
		weatherResponse.setId(ID);
		weatherResponse.setName(CITY);
		Sys sys = new Sys();
		sys.setCountry(COUNTRY);
		weatherResponse.setSys(sys);
		MainWeather mainDto = new MainWeather();
		mainDto.setTemp(TEMPERATURE);
		weatherResponse.setMain(mainDto);
		return weatherResponse;
	}

	private Weather getWeather() {
		return Weather.builder().id(ID).city(CITY).country(COUNTRY).temperature(TEMPERATURE).build();
	}
}
