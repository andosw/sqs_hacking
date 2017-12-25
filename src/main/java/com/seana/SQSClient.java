package com.seana;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    System.out.println("Inner messages... ");

    messages.stream().map(Message::getBody).forEach(s -> {
      InputStream bytes = new ByteArrayInputStream(s.getBytes());
      Map messageMap = null;
      try {
        messageMap = new ObjectMapper().readValue(bytes, Map.class);
      } catch (IOException e) {
        e.printStackTrace();
      }

      System.out.println(Arrays.toString(messageMap.entrySet().toArray()));
    });

//    JSONParser parser = new JSONParser();
//    JSONObject json = (JSONObject) parser.parse(stringToParse);


  }
}
