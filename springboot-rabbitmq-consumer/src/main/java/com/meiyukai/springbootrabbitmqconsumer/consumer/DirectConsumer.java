package com.meiyukai.springbootrabbitmqconsumer.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(value=@Queue,exchange = @Exchange(value = "direct_exchange",type = ExchangeTypes.DIRECT),key = {"debug","info","warning"})
    })
    public void consumeIowLevelMessage(String message){
        System.out.println("lowLevelMessage = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value=@Queue,exchange = @Exchange(value = "direct_exchange",type = ExchangeTypes.DIRECT), key = {"error"})
    })
    public void consumeHighLevelMessage(String message){
        System.out.println("highLevelMessage = " + message);
    }


}
