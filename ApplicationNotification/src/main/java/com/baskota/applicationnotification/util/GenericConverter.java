package com.baskota.applicationnotification.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public final class GenericConverter<T> {
    private final Class<T> clazz;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GenericConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Map<String, Object> toMap(T object) {
        return objectMapper.convertValue(object, Map.class);
    }

    public T fromMap(Map<String, Object> map) {
        return objectMapper.convertValue(map, clazz);
    }
}
