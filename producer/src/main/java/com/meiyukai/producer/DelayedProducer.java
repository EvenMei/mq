package com.meiyukai.producer;

import com.meiyukai.utils.ConnectionUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DelayedProducer {
    public static void main(String[] args) {
        Connection connection = ConnectionUtils.getConnection();
        try {
            Channel channel = connection.createChannel();
            Map<String,Object> arguments = new HashMap<>();
            arguments.put("x-delayed-type","direct");
            channel.exchangeDeclare("delayed_exchange","x-delayed-message",true,false,arguments);
            Map<String,Object> propMap = new HashMap<>();
            propMap.put("x-delay",2000);
            String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            AMQP.BasicProperties headers = new AMQP.BasicProperties
                    .Builder()
                    .headers(propMap)
                    .build();
            channel.basicPublish("delayed_exchange","info",headers,(sendTime+"delayed-message").getBytes());
            System.out.println( sendTime+ "  发送了消息 ： "  +"delayed-message" );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
