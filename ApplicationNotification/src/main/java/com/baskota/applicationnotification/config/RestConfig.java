package com.baskota.applicationnotification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean("restTemplate")
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
