package com.assignment.spring.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.spring.dto.WeatherDTO;
import com.assignment.spring.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = {"", "/v1"})
public class WeatherController {

	private final WeatherService weatherService;

	@Operation(summary = "Load weather information for the given city")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Weather information has been found successfully"),
			@ApiResponse(responseCode = "400", description = "Insufficient parameter"),
			@ApiResponse(responseCode = "401", description = "Invalid App Key"),
			@ApiResponse(responseCode = "404", description = "Weather information has not been found"),
			@ApiResponse(responseCode = "500", description = "Internal error occurred")
	})
	@RequestMapping("/weather/{city}")
	public WeatherDTO getWeather(@PathVariable(value = "city") final String city) {
		return weatherService.processWeather(city);
	}
}
