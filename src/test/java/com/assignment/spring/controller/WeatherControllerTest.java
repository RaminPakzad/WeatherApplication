package com.assignment.spring.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.assignment.spring.Application;
import com.assignment.spring.client.model.WeatherResponse;
import com.assignment.spring.domain.Weather;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.util.JsonUtil;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc()
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/sql/cleanup.sql")
@ActiveProfiles("test")
class WeatherControllerTest {

	@Value("http://localhost:${local.server.port}/v1/weather/berlin")
	private String baseUrl;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WeatherRepository weatherRepository;


	@Test
	void testGetWeather() throws Exception {
		final WeatherResponse weatherResponse = JsonUtil.readFile("/json/weather-response.json", WeatherResponse.class);
		when(restTemplate.getForEntity(anyString(), any()))
				.thenReturn(ResponseEntity.ok(weatherResponse));

		this.mockMvc.perform(get(baseUrl).contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.city").value("Amsterdam"))
				.andExpect(jsonPath("$.country").value("NL"))
				.andExpect(jsonPath("$.temperature").value(268.76));

		final List<Weather> weathers = weatherRepository.findAll();
		assertThat(weathers.size(), is(1));
		assertThat(weathers.get(0), allOf(
				hasProperty("id", notNullValue()),
				hasProperty("city", is("Amsterdam")),
				hasProperty("country", is("NL")),
				hasProperty("temperature", is(268.76))
		));
	}

}
