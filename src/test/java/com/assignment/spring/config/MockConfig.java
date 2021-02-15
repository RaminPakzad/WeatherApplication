package com.assignment.spring.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MockConfig {

    @Bean
    @Primary
    public RestTemplate mockedRestTemplate() {
        return Mockito.mock(RestTemplate.class);
    }

}
