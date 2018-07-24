package com.reda.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

public class ReceivedLogs {


    private static String exchange = "logs";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();
            //消费者也需要声明exchange
            channel.exchangeDeclare(exchange,"fanout");
            //然后消费者会声明一个Queue
            String queueName = channel.queueDeclare().getQueue();
            //对于fanout模式的exchange会直接忽略第三个参数
            channel.queueBind(queueName,exchange,"");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                    String message = new String(body,"UTF-8");
                    System.out.println("[X] Receive message: " + message);
                }
            };
            channel.basicConsume(queueName,true,consumer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
