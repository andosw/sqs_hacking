package com.seana;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SesDeliveryReport {
    /*
    timestamp
  processingTimeMillis
  recipients
  smtpResponse
  reportingMTA
   */
  @JsonProperty("timestamp")
  private String timestamp;

  @JsonProperty("processingTimeMillis")
  private String processingTimeMillis;

  @JsonProperty("recipients")
  private String[] recipients;

  @JsonProperty("smtpResponse")
  private String smtpResponse;

  @JsonProperty("reportingMTA")
  private String reportingMTA;

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getProcessingTimeMillis() {
    return processingTimeMillis;
  }

  public void setProcessingTimeMillis(String processingTimeMillis) {
    this.processingTimeMillis = processingTimeMillis;
  }

  public String[] getRecipients() {
    return recipients;
  }

  public void setRecipients(String[] recipients) {
    this.recipients = recipients;
  }

  public String getSmtpResponse() {
    return smtpResponse;
  }

  public void setSmtpResponse(String smtpResponse) {
    this.smtpResponse = smtpResponse;
  }

  public String getReportingMTA() {
    return reportingMTA;
  }

  public void setReportingMTA(String reportingMTA) {
    this.reportingMTA = reportingMTA;
  }
}
