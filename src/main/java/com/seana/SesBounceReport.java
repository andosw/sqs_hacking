package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SesBounceReport {
  @JsonProperty("bounceType")
  private String bounceType;

  public String getBounceType() {
    return bounceType;
  }

  public void setBounceType(String bounceType) {
    this.bounceType = bounceType;
  }
}
