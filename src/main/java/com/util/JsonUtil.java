package com.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 将Java对象序列化为JSON字符串
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 将JSON字符串反序列化为Java对象
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 将JSON字符串反序列化为List
    public static <T> List<T> fromJsonToList(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 将JSON字符串反序列化为Map
    public static <K, V> Map<K, V> fromJsonToMap(String json, Class<K> keyClazz, Class<V> valueClazz) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class, keyClazz, valueClazz));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}