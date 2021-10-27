package com.meiyukai.producer;

import com.meiyukai.utils.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class FanoutProducer {
    public static void main(String[] args) {
        Connection connection = ConnectionUtils.getConnection();
        try {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("fanout_exchange", BuiltinExchangeType.FANOUT.getType(),true,true,null);
            channel.basicPublish("fanout_exchange","",null," 千万别把秘密告诉风(fanout) ， 他会告诉整个森林 ".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
