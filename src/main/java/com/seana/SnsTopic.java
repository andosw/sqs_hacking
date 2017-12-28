package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SnsTopic {

  private static final ObjectMapper mapper = customObjectMapper(new ObjectMapper());

  @JsonProperty("MessageId")
  private String messageId;

  @JsonProperty("TopicArn")
  private String topicArn;

  @JsonProperty("Timestamp")
  private String timestamp;

  @JsonProperty("Message")
  private String message;

  public static SnsTopic fromJson(String json) {
    SnsTopic topic = null;
    try {
      topic = mapper.readValue(json, SnsTopic.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return topic;
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



  // TODO extract to helper
  private static ObjectMapper customObjectMapper(ObjectMapper mapper) {
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    return mapper;
  }
}
