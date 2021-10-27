package com.example.springbootrabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TopicProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;


    public void publishMessage(String message) {
        rabbitTemplate.convertAndSend("topic_exchange", "com.meiyukai.terminus", message);
    }
}
