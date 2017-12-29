package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SesFeedback {
  @JsonProperty("notificationType")
  private String notificationType;

  @JsonProperty("mail")
  private SesMailReport mail;

  @JsonProperty("delivery")
  private SesDeliveryReport delivery;

  @JsonProperty("bounce")
  private SesBounceReport bounce;

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

  public SesDeliveryReport getDelivery() {
    return delivery;
  }

  public void setDelivery(SesDeliveryReport delivery) {
    this.delivery = delivery;
  }

  public SesBounceReport getBounce() {
    return bounce;
  }

  public void setBounce(SesBounceReport bounce) {
    this.bounce = bounce;
  }
}
