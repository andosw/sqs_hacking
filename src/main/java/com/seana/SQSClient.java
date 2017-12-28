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

    System.out.println("Inner messages... ");

//    messages.stream().map(m -> {m.});

    //      InputStream bytes = new ByteArrayInputStream(s.getBytes());
//      Map messageMap = null;
//      try {
//        messageMap = new ObjectMapper().readValue(bytes, Map.class);
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//
//      System.out.println(Arrays.toString(messageMap.entrySet().toArray()));
//    messages.stream()
//        .map(Message::getBody)
//        .map(SnsNotification::new)
//        .forEach(notification -> {
//          System.out.println("Timestamp --> " + notification.getTimestamp());
//          // SesFeedback sesNotification = new SesFeedback(notification.getMessage());
//          SesFeedback sesNotification = notification.getMessage();
//          System.out.println("SES Notification Type --> " + sesNotification.getNotificationType());
//        });

    messages.stream()
        .map(Message::getBody)
        .map(SnsTopic::new)
        .forEach(notification -> {
          System.out.println("Timestamp --> " + notification.getTimestamp());
          // SesFeedback sesNotification = new SesFeedback(notification.getMessage());
//          SesFeedback sesNotification = notification.getMessage();
          System.out.println("SES Notification String --> " + notification.getMessage());
        });

    // https://forums.aws.amazon.com/thread.jspa?messageID=607800


//    Notification noti = toObject(jsonString, Notification.class);
//    Message msg = toObject(noti.getMessage(), Message.class);
//    noti.setObjMessage(msg);

//    JSONParser parser = new JSONParser();
//    JSONObject json = (JSONObject) parser.parse(stringToParse);


  }

}
