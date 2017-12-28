package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SesFeedback {

  private static final ObjectMapper mapper = customObjectMapper(new ObjectMapper());

  @JsonProperty("notificationType")
  private String notificationType;

  public static SesFeedback fromJson(String json) {
    SesFeedback feedback= null;
    try {
      feedback= mapper.readValue(json, SesFeedback.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return feedback;
  }

  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  // TODO extract to helper
  private static ObjectMapper customObjectMapper(ObjectMapper mapper) {
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    return mapper;
  }
}
