package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SnsTopic {

  @JsonProperty("MessageId")
  private String messageId;

  @JsonProperty("TopicArn")
  private String topicArn;

  @JsonProperty("Timestamp")
  private String timestamp;

  @JsonProperty("Message")
  private String message;

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

}
