package com.example.springbootrabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishMessage(String message){
        rabbitTemplate.convertAndSend("helloworld_queue","hello world queue demo");
    }
}
