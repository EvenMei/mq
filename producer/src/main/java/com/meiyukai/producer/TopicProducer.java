package com.meiyukai.producer;

import com.meiyukai.utils.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class TopicProducer {
    public static void main(String[] args) {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = null;
        try {
            channel = connection.createChannel();
            channel.exchangeDeclare("topic_exchange", BuiltinExchangeType.TOPIC,true,true,null);
            channel.basicPublish("topic_exchange","com.meiyukai.mq",null,"message is  com.meiyukai.mq".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
