package com.meiyukai.producer;

import com.meiyukai.utils.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class DirectProducer {
    public static void main(String[] args) {
        Connection connection = ConnectionUtils.getConnection();
        try {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("direct_exchange", BuiltinExchangeType.DIRECT,true,true,null);
            channel.basicPublish("direct_exchange","info",null,"info type message".getBytes());
//            channel.basicPublish("direct_exchange","error",null,"error type message".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
