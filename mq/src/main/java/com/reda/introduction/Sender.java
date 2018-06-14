package com.reda.introduction;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
* Rabbitmq官方教程一
* Helloworld
* */
public class Sender {
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
            String msg = "hello world";
            channel.basicPublish("",queueName,null,msg.getBytes());
            System.out.println("[x] Send " + msg);
            channel.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
