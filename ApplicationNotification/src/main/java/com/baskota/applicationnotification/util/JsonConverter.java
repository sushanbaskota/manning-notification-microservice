package com.baskota.applicationnotification.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public final class JsonConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonConverter() {
        objectMapper
                .setConfig(objectMapper.getSerializationConfig().without(SerializationFeature.FAIL_ON_EMPTY_BEANS))
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public <T> T toObject(String jsonString, Class<T> clazz) {
        String actualPayload = StringUtils.hasText(jsonString) ? jsonString : "{}";

        try {
            return objectMapper.readValue(actualPayload, clazz);
        } catch (Exception ex) {
            throw new RuntimeException("unable to deserialize content", ex);
        }
    }

    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            throw new RuntimeException("unable to serialize content", ex);
        }
    }

    public <T> T cloneObject(Object object, Class<T> clazz) {
        return toObject(toJson(object), clazz);
    }
}
