package com.seana;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringEscapeUtils;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SQSClient {

  private final AmazonSQS sqs;
  private static final String DELIVERY_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/547897653276/ses_delivery_queue";
  private static final ObjectMapper mapper = customObjectMapper(new ObjectMapper());

  public SQSClient() {
    sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
  }

  public void printMessages() {
    List<Message> messages = sqs.receiveMessage(DELIVERY_QUEUE_URL).getMessages();
    System.out.println("Message length: " + messages.size());

    // https://forums.aws.amazon.com/thread.jspa?messageID=607800
    messages.stream()
        .map(Message::getBody)
        .map(body -> {
          System.out.println("SQS Body -> " + body);
          return body;
        })
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

  private static ObjectMapper customObjectMapper(ObjectMapper mapper) {
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    return mapper;
  }

}
