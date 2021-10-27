package com.meiyukai.consume;

import com.meiyukai.utils.ConnectionUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class TopicConsumer {
    public static void main(String[] args) {
        new Thread(() -> {
            oneConsumer();
        }).start();

        new Thread(() -> {
            twoConsumer();
        }).start();
    }

    public static void oneConsumer(){
        Connection connection = ConnectionUtils.getConnection();
        try {
            Channel channel = connection.createChannel();
            String one_queue = channel.queueDeclare().getQueue();
            channel.exchangeDeclare("topic_exchange", BuiltinExchangeType.TOPIC,true,true,null);
            channel.queueBind(one_queue,"topic_exchange","*.meiyuki.#");
            channel.basicConsume(one_queue,true , new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("message is : " + new String(body));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void twoConsumer(){
        Connection connection = ConnectionUtils.getConnection();
        try {
            Channel channel = connection.createChannel();
            String two_queue = channel.queueDeclare().getQueue();
            channel.exchangeDeclare("topic_exchange", BuiltinExchangeType.TOPIC,true,true,null);
            channel.queueBind(two_queue,"topic_exchange","com.*.#");
            channel.basicConsume(two_queue,true , new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("message is : " + new String(body));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
