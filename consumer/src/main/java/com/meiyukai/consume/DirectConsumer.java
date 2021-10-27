package com.meiyukai.consume;

import com.meiyukai.utils.ConnectionUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class DirectConsumer {
    public static void main(String[] args)  {
        new Thread(() -> {
            commonConsumer();
        }).start();

        new Thread(() -> {
            heavyConsumer();
        }).start();
    }

    public static void commonConsumer() {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("direct_exchange", BuiltinExchangeType.DIRECT,true,true,null);
            String queue_name = channel.queueDeclare().getQueue();
            channel.queueBind(queue_name,"direct_exchange","debug");
            channel.queueBind(queue_name,"direct_exchange","info");
            channel.queueBind(queue_name,"direct_exchange","warning");
            channel.basicConsume(queue_name,true,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("common message [debug/info/warning] :  "+new String(body));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void heavyConsumer(){
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("direct_exchange", BuiltinExchangeType.DIRECT,true,true,null);
            String queue_name = channel.queueDeclare().getQueue();
            channel.queueBind(queue_name,"direct_exchange","error");
            channel.basicConsume(queue_name,true,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("common message [error] :  "+new String(body));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
