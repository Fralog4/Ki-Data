package com.app.Ki_Data.security.jwtConfig

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val authFilter: JwtAuthenticationFilter,
    private val authenticationProvider: AuthenticationProvider
) {
    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.csrf { csrf -> csrf.disable() }

            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/v3/api-docs/**", "/swagger-ui/**").anonymous()
                auth.requestMatchers("/auth/**", "/kiData/characters/**", "/kiData/ai/**").permitAll()
                auth.anyRequest().authenticated()
            }
        httpSecurity.authenticationProvider(authenticationProvider)
        httpSecurity.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter::class.java)

        return httpSecurity.build()
    }
}