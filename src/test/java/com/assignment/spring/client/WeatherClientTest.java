package com.assignment.spring.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.assignment.spring.Application;
import com.assignment.spring.client.model.MainWeather;
import com.assignment.spring.client.model.Sys;
import com.assignment.spring.client.model.WeatherResponse;
import com.assignment.spring.domain.Weather;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("test")
class WeatherClientTest {
	private static final Integer ID = 1;

	private static final String CITY = "Amsterdam";

	private static final String COUNTRY = "Netherlands";

	private static final Double TEMPERATURE = 20.1;

	@Autowired
	private WeatherClient weatherClient;

	@Autowired
	private RestTemplate restTemplate;


	@Test
	void throwExceptionWhenCityIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> weatherClient.getWeather(""));
	}

	@Test
	void throwExceptionWhenCityIsNull() {
		assertThrows(IllegalArgumentException.class, () -> weatherClient.getWeather(null));
	}

	@Test
	void throwExceptionWhenCityIsWhitespace() {
		assertThrows(IllegalArgumentException.class, () -> weatherClient.getWeather(" "));
	}

	@Test
	void getWeather() {
		when(restTemplate.getForEntity(anyString(), any()))
				.thenReturn(ResponseEntity.ok(getInboundWeatherDto()));

		Optional<Weather> weather = weatherClient.getWeather(CITY);
		assertTrue(weather.isPresent());
		assertThat(weather.get(), allOf(
				hasProperty("id", is(ID)),
				hasProperty("city", is(CITY)),
				hasProperty("country", is(COUNTRY)),
				hasProperty("temperature", is(TEMPERATURE))
		));
	}


	private WeatherResponse getInboundWeatherDto() {
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
}
