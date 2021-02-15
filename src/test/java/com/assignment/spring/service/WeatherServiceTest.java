package com.assignment.spring.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.assignment.spring.client.WeatherClient;
import com.assignment.spring.domain.Weather;
import com.assignment.spring.dto.WeatherDTO;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.repository.WeatherRepository;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

	private static final Integer ID = 1;

	private static final String CITY = "Amsterdam";

	private static final String COUNTRY = "Netherlands";

	private static final Double TEMPERATURE = 20.1;

	@Mock
	private WeatherClient weatherClient;

	@Mock
	private WeatherMapper weatherMapper;

	@Mock
	private WeatherRepository weatherRepository;

	@InjectMocks
	private WeatherServiceImpl weatherService;

	@Test
	void loadAndSave() {
		Weather weather = getWeather();
		Weather persistedWeather = weather.toBuilder().id(ID).build();

		when(weatherClient.getWeather(CITY)).thenReturn(Optional.of(weather));
		when(weatherRepository.save(weather)).thenReturn(persistedWeather);
		when(weatherMapper.map(persistedWeather)).thenReturn(getWeatherDto());

		WeatherDTO weatherDTO = weatherService.processWeather(CITY);

		assertThat(weatherDTO, allOf(
				hasProperty("id", is(ID)),
				hasProperty("city", is(CITY)),
				hasProperty("country", is(COUNTRY)),
				hasProperty("temperature", is(TEMPERATURE))
		));
	}

	private Weather getWeather() {
		return Weather.builder()
				.city(CITY)
				.country(COUNTRY)
				.temperature(TEMPERATURE)
				.build();
	}

	private WeatherDTO getWeatherDto() {
		return WeatherDTO.builder()
				.id(ID)
				.city(CITY)
				.country(COUNTRY)
				.temperature(TEMPERATURE)
				.build();
	}
}
