/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.police.innovation.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;

/**
 * JSON serializer/deserializer utility for domain object caching.
 * 
 */
public final class JsonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * Suppresses default constructor, ensuring non-instantiability.
     */
    private JsonUtil() {
    }

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    /**
     * @return a configured ObjectMapper instance
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /**
     * Creates an instance of default MappingJackson2HttpMessageConverter and assigns HandlerInstantiator to it
     * 
     * @param handlerInstantiator handlerInstantiator
     * @return an instance of a Json MessageConverter
     */
    public static MappingJackson2HttpMessageConverter getMessageConverter(final HandlerInstantiator handlerInstantiator) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(getObjectMapper());
        converter.getObjectMapper().setHandlerInstantiator(handlerInstantiator);
        return converter;
    }

    /**
     * Unserializes JSON to an Object.
     * 
     * @param <T> the generic type
     * @param json the JSON string
     * @param type desired output type
     * @return unserialized object
     */
    public static <T> T fromJson(final String json, final Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        }
        catch (IOException e) {
            LOG.error("Failed to convert Mashery header" + e);
            return null;
        }
    }
    

    /**
     * Gets the object as JSON string.
     * 
     * @param object the request or response to/from the REST service.
     * @return the object as JSON string
     */
    public static String getObjectAsJsonString(final Object object) {
        String jsonString = null;
        try {
            jsonString = OBJECT_MAPPER.writeValueAsString(object);
        }
        catch (IOException ioException) {
            LOG.error("IOException while writing the imput object as JSON string {}", ioException);
        }
        return jsonString;
    }
}