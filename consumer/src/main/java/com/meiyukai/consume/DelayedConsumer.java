package com.meiyukai.consume;

import com.meiyukai.utils.ConnectionUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DelayedConsumer {
    public static void main(String[] args) {
        Connection connection = ConnectionUtils.getConnection();
        try {
            Channel channel = connection.createChannel();
            Map<String,Object> arguments = new HashMap<>();
            arguments.put("x-delayed-type","direct");
            channel.exchangeDeclare("delayed_exchange","x-delayed-message",true,false, arguments);
            String queue_name = channel.queueDeclare().getQueue();
            channel.queueBind(queue_name,"delayed_exchange","info");
            channel.basicConsume(queue_name,true,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String receiveTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    System.out.println(receiveTime+ "   接受到消息：   "+new String(body));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
