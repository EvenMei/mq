package com.meiyukai.springbootrabbitmqconsumer.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldConsumer {

    @RabbitListener(queuesToDeclare = @Queue(name = "helloworld_queue"))
    public void consumeMessage(String message){
        System.out.println(" Hello World  message : "+message);
    }
}
