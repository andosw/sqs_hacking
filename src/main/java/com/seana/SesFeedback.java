package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SesFeedback {
  @JsonProperty("notificationType")
  private String notificationType;

  public SesFeedback(String json) {
    setAllValues(json);
  }

  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public void setAllValues(String json) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      Map<String, String> map = mapper.readValue(json,
          new TypeReference<HashMap<String, String>>() {
          });

      this.setNotificationType(map.get("notificationType"));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
