package com.osho81.economyapp.config;

import com.osho81.economyapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


// Find User, with method that returns user

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {

        // Use customized crud method in user repo
        return username -> userRepository.findByEmail(username)
                // Returns optional; so handle exception:
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
