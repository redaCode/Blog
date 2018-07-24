package com.reda.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLog {
    private static String exchange = "logs";
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();
            //声明fanout的Exchange
            channel.exchangeDeclare(exchange,"fanout");

            String message ="hello";

            //向Exchange发消息
            channel.basicPublish(exchange,"",null,message.getBytes());
            System.out.println("[X] send message: " + message);

//            channel.close();
//            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
