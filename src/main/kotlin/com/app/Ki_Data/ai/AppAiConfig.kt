package com.app.Ki_Data.ai

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class AppAiConfig {
    @Bean
    fun restTemplate() = RestTemplate()
}