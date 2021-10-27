package com.meiyukai.springbootrabbitmqconsumer.consumer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DelayedConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue/*(value = "delayed_queue",declare = "true",durable = "true",autoDelete = "true")*/,
                    exchange=@Exchange(value = "delayed_exchange", type= "x-delayed-message"   /*type="x-delayed-message"*/,durable = "true",autoDelete = "true")
                    ,key={"info"}
            )
    })
    public void consumeMessage(String message){
        System.out.println(" message =      " + message +"    NOW IS "+ new Date());
    }
}
