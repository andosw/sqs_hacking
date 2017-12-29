package com.seana;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonDeserializer {
  private static final ObjectMapper mapper = customObjectMapper(new ObjectMapper());

  public static <T> T fromJson(String json, Class<T> tClass) {
    T output;
    try {
      output = mapper.readValue(json, tClass);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return output;
  }

  private static ObjectMapper customObjectMapper(ObjectMapper mapper) {
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    return mapper;
  }
}
