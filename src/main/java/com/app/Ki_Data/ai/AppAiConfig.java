package com.app.Ki_Data.ai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppAiConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
