package com.example.springbootrabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkQueueProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishMessage(){
        for(int i = 0; i<10; i++){
            rabbitTemplate.convertAndSend("work_queue" , ( i+"   work_queue_message") );
        }

    }
}
