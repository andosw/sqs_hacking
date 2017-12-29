package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class SesFeedback {
  @JsonProperty("notificationType")
  private String notificationType;

  @JsonProperty("mail")
  private JsonNode mail;

  public String getNotificationType() {
    return notificationType;
  }

  public JsonNode getMail() {
    return mail;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public void setMail(JsonNode mail) {
    this.mail = mail;
  }
}
