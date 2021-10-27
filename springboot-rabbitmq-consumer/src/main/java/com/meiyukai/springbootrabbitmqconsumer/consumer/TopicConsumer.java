package com.meiyukai.springbootrabbitmqconsumer.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange=@Exchange(value = "topic_exchange",type = ExchangeTypes.TOPIC),key = {"#.meiyukai.#"})
    })
    public void consumeMessageOne(String message){
       System.out.println("messageOne :   "+ message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange=@Exchange(value = "topic_exchange",type = ExchangeTypes.TOPIC),key = {"*.meiyuki.terminus"})
    })
    public void consumeMessageTwo(String message){
        System.out.println("messageTwo :   "+ message);
    }
}
