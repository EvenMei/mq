package com.example.springbootrabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DelayedProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishDelayedMessage(String message){
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        rabbitTemplate.convertAndSend("delayed_exchange","info",message+now , msg ->{
            msg.getMessageProperties().setHeader("x-delay", 3000);
            return msg;
        });
    }
}
