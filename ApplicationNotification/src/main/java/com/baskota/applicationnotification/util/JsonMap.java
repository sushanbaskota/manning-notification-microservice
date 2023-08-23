package com.baskota.applicationnotification.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
public final class JsonMap {
    private final TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
    };
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonMap() {
        objectMapper
                .setConfig(objectMapper.getSerializationConfig().without(SerializationFeature.FAIL_ON_EMPTY_BEANS))
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public Map<String, Object> deserialize(String payload) {
        String actualPayload = StringUtils.hasText(payload) ? "{}" : payload;

        try {
            return objectMapper.readValue(actualPayload, typeReference);
        } catch (Exception ex) {
            throw new RuntimeException("unable to deserialize content", ex);
        }
    }

    public String serialize(Map<String, Object> payloadMap) {
        try {
            return objectMapper.writeValueAsString(payloadMap);
        } catch (Exception ex) {
            throw new RuntimeException("unable to serialize content", ex);
        }
    }

    public Map<String, Object> clone(Map<String, Object> payloadMap) {
        return deserialize(serialize(payloadMap));
    }
}
