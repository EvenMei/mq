package com.meiyukai.consume;

import com.meiyukai.utils.ConnectionUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WorkQueueConsume {
    public static void main(String[] args) {
        new Thread(() -> {
            consumeFaster();
        }).start();

        new Thread(() -> {
            consumeSlower();
        }).start();
    }

    public static void consumeFaster(){
        Connection connection = ConnectionUtils.getConnection();
        try {
            Channel channel = connection.createChannel();
            channel.basicQos(1);
            channel.queueDeclare("workqueue",true,false,true,null);
            channel.basicConsume("workqueue",false, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("workqueue  consumeFaster === got message : " + new String(body));
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void consumeSlower(){
        Connection connection = ConnectionUtils.getConnection();
        try {
            Channel channel = connection.createChannel();
            channel.basicQos(1);
            channel.queueDeclare("workqueue",true,false,true,null);
            channel.basicConsume("workqueue",false, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("workqueue  consumeSlower === got message : " + new String(body));
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
