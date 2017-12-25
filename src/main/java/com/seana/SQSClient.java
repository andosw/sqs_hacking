package com.seana;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;


import java.util.List;

public class SQSClient {

  private final AmazonSQS sqs;
  private static final String DELIVERY_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/547897653276/ses_delivery_queue";

  public SQSClient() {
    sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
  }

  public void printMessages() {
    List<Message> messages = sqs.receiveMessage(DELIVERY_QUEUE_URL).getMessages();
    System.out.println("Message length: " + messages.size());

    messages.forEach(System.out::println);
  }
}
