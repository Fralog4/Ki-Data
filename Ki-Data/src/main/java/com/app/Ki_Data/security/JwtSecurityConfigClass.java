package com.app.Ki_Data.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@EnableWebSecurity
@Configuration
public class JwtSecurityConfigClass {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csfr->csfr.ignoringRequestMatchers(toH2Console()).disable())
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/characters").permitAll()
                        .requestMatchers("/characters/{id}").permitAll()
                        .requestMatchers("/characters/delete/**").hasRole("ADMIN")
                        .requestMatchers("/characters/create").hasRole("ADMIN")
                        .requestMatchers(toH2Console()).permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .headers(headers->headers.frameOptions(frameOptions-> frameOptions.sameOrigin()))
                .oauth2ResourceServer((oauth2)->oauth2.jwt(Customizer.withDefaults()));
        return httpSecurity.build();
    }

}
