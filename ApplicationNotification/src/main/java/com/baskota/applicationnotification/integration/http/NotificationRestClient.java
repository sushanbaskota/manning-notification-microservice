package com.baskota.applicationnotification.integration.http;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

@Component
@RequiredArgsConstructor
public class NotificationRestClient {

    private final RestTemplate restTemplate;

    public String execute(HttpMethod httpMethod, String url, String requestPayload) {
        try {
            HttpHeaders httpHeaders = buildHeaders();

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod,
                    new HttpEntity<>(requestPayload, httpHeaders), String.class);

            return responseEntity.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException | UnknownHttpStatusCodeException ex) {
            throw new RuntimeException("error on executing http request", ex);
        }
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return httpHeaders;
    }
}
