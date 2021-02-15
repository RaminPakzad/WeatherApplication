package com.assignment.spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "weather")
public class Weather {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String city;
	private String country;
	private Double temperature;
}
