package com.demo.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// test
public class JacksonDemo {
    public static void main(String[] args) throws IOException {
        System.out.println("Jackson Demo");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        // People people = new People( "John", 1,new String[]{"Reading", "Travelling"});
        // String json = objectMapper.writeValueAsString(people);
        // System.out.println(json);
        String yourJson = "{\"na1me\":\"John\",\"age\":1,\"hobbies\":[\"Reading\",\"Travelling\"],\"ag2e\":1}";
        People p2 = objectMapper.readValue(yourJson, People.class);
        System.out.println(p2);
    }
}
