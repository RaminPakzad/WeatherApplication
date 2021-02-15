package com.assignment.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.spring.domain.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}
