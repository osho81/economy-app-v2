package com.osho81.economyapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// Spring security v 3

// Responsible for all http security in the app

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .csrf().disable()
                .authorizeHttpRequests()

                // Create a white list
                .requestMatchers("urls")
                .permitAll()

                // All else must be authenticated
                .anyRequest()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)


        return http.build();

    }

}
