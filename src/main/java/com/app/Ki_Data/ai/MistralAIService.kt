package com.app.Ki_Data.ai

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


@Service
class MistralAIService(
    @Value("\${spring.ai.mistralai.base-url}") private val baseUrl: String,
    @Value("\${spring.ai.mistralai.api-key}") private val apiToken: String,
    @Value("\${spring.ai.mistralai.agent.id}") private val agentId: String,
    private val restTemplate: RestTemplate
) {

    fun postRequestToAI(endpoint: String, payload: String): Result<String> {

        return runCatching {

            val fullUrl = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment(endpoint)
                .toUriString()

            val headers = HttpHeaders().apply {
                setBearerAuth(apiToken)
                contentType = MediaType.APPLICATION_JSON
            }

            val requestEntity = HttpEntity(payload, headers)

            val response = restTemplate.exchange(
                fullUrl,
                HttpMethod.POST,
                requestEntity,
                String::class.java
            )

            response.body ?: throw RuntimeException("Empty response")
        }
    }

}