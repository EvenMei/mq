package com.meiyukai.producer;

import com.meiyukai.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class HelloWorldQueueProducer {
    public static void main(String[] args) {
        Connection connection = ConnectionUtils.getConnection();
        try {
            Channel channel = connection.createChannel();
            channel.basicPublish("","helloworldqueue",null,"hello i am helloworld queue".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
