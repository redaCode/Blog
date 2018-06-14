package com.reda.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
* Rabbitmq官方教程二
* Work Queues
* 当有多个消费者时，消息会每个消费者分一个，然后重复分。
* */
public class NewTask {
    private static String queueName = "hello";
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            //虚拟的socket连接,连接其他机器只需配置主机的IP
            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();
            //queue具有幂等性
            channel.queueDeclare(queueName,false,false,false,null);
            String message = getMessage(args);

            channel.basicPublish("", queueName, null, message.getBytes());
            channel.basicPublish("", queueName, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            channel.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static String getMessage(String[] strings){
        if (strings.length < 1)
            return "Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
