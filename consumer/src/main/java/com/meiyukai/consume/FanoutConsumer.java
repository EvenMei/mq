package com.meiyukai.consume;

import com.meiyukai.utils.ConnectionUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class FanoutConsumer {
    public static void main(String[] args) {
        new Thread(() -> {
            oneFanoutConsumer();
        }).start();

        new Thread(() -> {
            twoFanoutConsumer();
        }).start();

    }

    public static void oneFanoutConsumer(){
        Connection connection = ConnectionUtils.getConnection();
        try {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("fanout_exchange", BuiltinExchangeType.FANOUT.getType(),true,true,null);
            String queue_name = channel.queueDeclare().getQueue();
            channel.queueBind(queue_name,"fanout_exchange","");
            channel.basicConsume(queue_name,true,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("consume1 got message : " + new String(body));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void twoFanoutConsumer(){
        Connection connection = ConnectionUtils.getConnection();
        try {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("fanout_exchange", BuiltinExchangeType.FANOUT.getType(),true,true,null);
            String queue_name = channel.queueDeclare().getQueue();
            channel.queueBind(queue_name,"fanout_exchange","");
            channel.basicConsume(queue_name,true,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("consume2 got message : " + new String(body));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
