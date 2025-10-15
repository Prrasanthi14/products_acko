package com.products.products.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
            httpSecurity.authorizeHttpRequests(request->request
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/products/createProduct/**").hasAnyRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/v1/products/productDetails/**").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/v1/products/productDetails/updateOrder/**").hasRole("USER")
                    .anyRequest().authenticated()
            );
        return httpSecurity.build();
    }
}
