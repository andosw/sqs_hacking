package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class SesMailReport {
  @JsonProperty("timestamp")
  private String timestamp;

  @JsonProperty("source")
  private String source;

  @JsonProperty("sourceIp")
  private String sourceIp;

  @JsonProperty("messageId")
  private String messageId;

  @JsonProperty("destination")
  private String[] destination;

  @JsonProperty("headers")
  private List<Map<String, String>> headers;

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

  public String getSourceIp() {
    return sourceIp;
  }

  public void setSourceIp(String sourceIp) {
    this.sourceIp = sourceIp;
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String[] getDestination() {
    return destination;
  }

  public void setDestination(String[] destination) {
    this.destination = destination;
  }

  public List<Map<String, String>> getHeaders() {
    return headers;
  }

  public void setHeaders(List<Map<String, String>> headers) {
    this.headers = headers;
  }
}
