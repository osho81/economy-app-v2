package com.osho81.economyapp.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// SpringBoot 3, spring security 6 syntax

// Responsible for all http security in the app


import static com.osho81.economyapp.user.Permission.ADMIN_CREATE;
import static com.osho81.economyapp.user.Permission.ADMIN_DELETE;
import static com.osho81.economyapp.user.Permission.ADMIN_READ;
import static com.osho81.economyapp.user.Permission.ADMIN_UPDATE;
import static com.osho81.economyapp.user.Permission.MANAGER_CREATE;
import static com.osho81.economyapp.user.Permission.MANAGER_DELETE;
import static com.osho81.economyapp.user.Permission.MANAGER_READ;
import static com.osho81.economyapp.user.Permission.MANAGER_UPDATE;
import static com.osho81.economyapp.user.Role.ADMIN;
import static com.osho81.economyapp.user.Role.MANAGER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {


    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .cors(AbstractHttpConfigurer::disable)
//                .csrf.disable() // Deprecated style
                .csrf(AbstractHttpConfigurer::disable)

//                .formLogin(AbstractHttpConfigurer::disable)

//                .securityMatcher("/**") // Not making any difference

                // Create a white list:
                // .authorizeHttpRequests() // Deprecated style
                .authorizeHttpRequests(auth -> auth
                        // Base endpoint for auth management: /api/v1/auth
                        .requestMatchers("/api/v1/auth")
                        .permitAll()


                        .requestMatchers("/api/v1/**").hasAnyRole(ADMIN.name(), MANAGER.name())

                        // All else must be authenticated
                        .anyRequest()
                        .authenticated()

                )

//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Deprecated style
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))


                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

}
