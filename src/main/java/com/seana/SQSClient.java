package com.seana;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;

public class SQSClient {

  private final AmazonSQS sqs;
  private static final String DELIVERY_QUEUE_URL  = "https://sqs.us-west-2.amazonaws.com/547897653276/ses_delivery_queue";
  private static final String BOUNCE_QUEUE_URL    = "https://sqs.us-west-2.amazonaws.com/547897653276/ses_bounce_queue";
  private static final String COMPLAINT_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/547897653276/ses_complaint_queue";

  private static final ObjectMapper mapper = customObjectMapper(new ObjectMapper());

  public SQSClient() {
    sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
  }

  public void getDeliveryMessages() {
    List<Message> messages = sqs.receiveMessage(DELIVERY_QUEUE_URL).getMessages();
    System.out.println("Message length: " + messages.size());

    // https://forums.aws.amazon.com/thread.jspa?messageID=607800
    messages.stream()
        .map(Message::getBody)
        .peek(body -> System.out.println("SQS Body -> " + body))
        .map(body -> JsonDeserializer.fromJson(body, SnsTopic.class))
        .map(topic -> {
          System.out.println("Topic Timestamp --> " + topic.getTimestamp());

          // TODO: figure out how to automatically parse inner Message like the inner mail
          SesFeedback sesFeedback = JsonDeserializer.fromJson(topic.getMessage(), SesFeedback.class);
          System.out.println("SesFeedback -> " + sesFeedback);
          return sesFeedback;
        })
        .forEach(sesFeedback -> {
          System.out.println("SES Feedback type: " + sesFeedback.getNotificationType());
          SesMailReport sesMailReport = sesFeedback.getMail();
          System.out.println("Mail Report: " + sesMailReport.getHeaders());

          SesDeliveryReport sesDeliveryReport = sesFeedback.getDelivery();
          System.out.println("Delivery Report: " + sesDeliveryReport.getReportingMTA());
        });
    // .forEach(sesMailReport -> System.out.println(sesMailReport.getSource()));
  }

  public void getBounceMessages() {
    System.out.println("Fetching Bounce Messages...");
    ReceiveMessageRequest request = new ReceiveMessageRequest();
    request.setQueueUrl(BOUNCE_QUEUE_URL);
    request.setMaxNumberOfMessages(5);
    List<Message> messages = sqs.receiveMessage(request).getMessages();
    System.out.println("Message length: " + messages.size());

    messages.stream()
        .peek(msg -> System.out.println("SQS Message: " + msg))
        .map(Message::getBody)
        .map(body -> JsonDeserializer.fromJson(body, SnsTopic.class))
        .map(topic -> JsonDeserializer.fromJson(topic.getMessage(), SesFeedback.class))
        .forEach(sesFeedback -> {
          System.out.println("SES Feedback type: " + sesFeedback.getNotificationType());

          SesBounceReport sesBounceReport = sesFeedback.getBounce();
          System.out.println("Bounce Report: " + sesBounceReport.getBounceType());

          SesMailReport sesMailReport = sesFeedback.getMail();
          System.out.println("Mail Report: " + sesMailReport.getHeaders());
        });
  }

  public void getComplaintMessages() {
    System.out.println("Fetching Complaint Messages...");
    ReceiveMessageRequest request = new ReceiveMessageRequest();
    request.setQueueUrl(COMPLAINT_QUEUE_URL);
    request.setMaxNumberOfMessages(5);
    List<Message> messages = sqs.receiveMessage(request).getMessages();
    System.out.println("Message length: " + messages.size());

    messages.stream()
        .peek(msg -> System.out.println("SQS Message: " + msg))
        .map(Message::getBody)
        .map(body -> JsonDeserializer.fromJson(body, SnsTopic.class))
        .map(topic -> JsonDeserializer.fromJson(topic.getMessage(), SesFeedback.class))
        .forEach(sesFeedback -> {
          System.out.println("SES Feedback type: " + sesFeedback.getNotificationType());

          SesComplaintReport sesComplaintReport = sesFeedback.getComplaint();
          System.out.println("Complaint Report: " + sesComplaintReport.getComplaintFeedbackType());

          SesMailReport sesMailReport = sesFeedback.getMail();
          System.out.println("Mail Report: " + sesMailReport.getHeaders());
        });
  }

  private static ObjectMapper customObjectMapper(ObjectMapper mapper) {
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    return mapper;
  }

}
