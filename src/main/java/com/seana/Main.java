package com.seana;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World");
        SQSClient client = new SQSClient();
        client.printMessages();

        System.out.println("Exiting");
    }
}