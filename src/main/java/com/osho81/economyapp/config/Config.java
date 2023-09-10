package com.osho81.economyapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

// Not part of security implementation

@Configuration
@EnableJpaAuditing
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
