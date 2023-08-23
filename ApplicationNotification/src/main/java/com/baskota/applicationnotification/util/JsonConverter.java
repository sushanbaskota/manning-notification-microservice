package com.baskota.applicationnotification.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public final class JsonConverter {
    private final TypeReference<Object> typeReference = new TypeReference<>() {
    };
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonConverter() {
        objectMapper
                .setConfig(objectMapper.getSerializationConfig().without(SerializationFeature.FAIL_ON_EMPTY_BEANS))
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public Object fromJson(String payload) {
        String actualPayload = StringUtils.hasText(payload) ? "{}" : payload;

        try {
            return objectMapper.readValue(actualPayload, typeReference);
        } catch (Exception ex) {
            throw new RuntimeException("unable to deserialize content", ex);
        }
    }

    public String toJson(Object payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (Exception ex) {
            throw new RuntimeException("unable to serialize content", ex);
        }
    }

    public Object clone(Object payload) {
        return fromJson(toJson(payload));
    }
}
