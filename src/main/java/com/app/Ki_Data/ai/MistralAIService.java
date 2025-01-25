package com.app.Ki_Data.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MistralAIService {

    @Value("${spring.ai.mistralai.base-url}")
    private String baseUrl;

    @Value("${spring.ai.mistralai.api-key}")
    private String apiToken;

    private final RestTemplate restTemplate;

    public MistralAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String postRequestToAI(String endpoint, String payload) {
        try {
            String fullUrl = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .pathSegment(endpoint)
                    .toUriString();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiToken);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);

            ResponseEntity<String> response = restTemplate.exchange(fullUrl, HttpMethod.POST, requestEntity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return "Error: " + response.getStatusCode();
            }
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }
}