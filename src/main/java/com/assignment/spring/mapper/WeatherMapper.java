package com.assignment.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.assignment.spring.client.model.WeatherResponse;
import com.assignment.spring.domain.Weather;
import com.assignment.spring.dto.WeatherDTO;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

	WeatherDTO map(Weather weather);

	@Mapping(source = "name", target = "city")
	@Mapping(source = "sys.country", target = "country")
	@Mapping(source = "main.temp", target = "temperature")
	Weather map(WeatherResponse response);
}
