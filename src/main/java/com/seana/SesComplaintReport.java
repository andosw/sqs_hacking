package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SesComplaintReport {
  @JsonProperty("complaintFeedbackType")
  private String complaintFeedbackType;

  public String getComplaintFeedbackType() {
    return complaintFeedbackType;
  }

  public void setComplaintFeedbackType(String complaintFeedbackType) {
    this.complaintFeedbackType = complaintFeedbackType;
  }
}
