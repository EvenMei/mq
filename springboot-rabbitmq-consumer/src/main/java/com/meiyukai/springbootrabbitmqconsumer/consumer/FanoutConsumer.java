package com.meiyukai.springbootrabbitmqconsumer.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumer {

    @RabbitListener(bindings = {@QueueBinding(value = @Queue,exchange = @Exchange(value = "fanout_exchange",type = ExchangeTypes.FANOUT))})
    public void consumeMessage1(String message){
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue,exchange = @Exchange(value = "fanout_exchange",type=ExchangeTypes.FANOUT))})
    public void consumeMessage2(String message){
        System.out.println("message2 = " + message);
    }


}
