package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SnsTopic {
  @JsonProperty("MessageId")
  private String messageId;

  @JsonProperty("TopicArn")
  private String topicArn;

  @JsonProperty("Timestamp")
  private String timestamp;

  @JsonProperty("Message")
  private String message;

  public SnsTopic(String json) {
    setAllValues(json);
  }

  public String getMessageId() {
    return messageId;
  }

  public String getMessage() {
    return message;
  }

  public String getTopicArn() {
    return topicArn;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setTopicArn(String topicArn) {
    this.topicArn = topicArn;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public void setAllValues(String json) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      Map<String, String> map = mapper.readValue(json,
          new TypeReference<HashMap<String, String>>() {
          });

      this.setMessageId(map.get("MessageId"));
      this.setMessage(map.get("Message"));
      this.setTopicArn(map.get("TopicArn"));
      this.setTimestamp(map.get("Timestamp"));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
