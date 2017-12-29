package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SesMailReport {
  @JsonProperty("timestamp")
  private String timestamp;

  @JsonProperty("source")
  private String source;

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }
}
