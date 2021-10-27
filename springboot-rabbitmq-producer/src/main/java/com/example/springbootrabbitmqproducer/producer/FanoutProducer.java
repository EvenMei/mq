package com.example.springbootrabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void produceMessage(String message){
        rabbitTemplate.convertAndSend("fanout_exchange","", message);
    }
}
