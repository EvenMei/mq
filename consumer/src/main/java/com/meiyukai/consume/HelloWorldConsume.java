package com.meiyukai.consume;

import com.meiyukai.utils.ConnectionUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class HelloWorldConsume {
    public static void main(String[] args) {
        Connection connection = ConnectionUtils.getConnection();
        try {
            Channel channel = connection.createChannel();
            channel.queueDeclare("helloworldqueue",false,true,true,null);
            channel.basicConsume("helloworldqueue",true, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("HelloWorldConsume got message : " + new String(body));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
