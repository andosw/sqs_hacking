package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SesFeedback {
  @JsonProperty("notificationType")
  private String notificationType;

  @JsonProperty("mail")
  private SesMailReport mail;

  public String getNotificationType() {
    return notificationType;
  }

  public SesMailReport getMail() {
    return mail;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public void setMail(SesMailReport mail) {
    this.mail = mail;
  }
}
