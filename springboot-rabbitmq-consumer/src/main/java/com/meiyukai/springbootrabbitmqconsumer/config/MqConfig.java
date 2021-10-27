package com.meiyukai.springbootrabbitmqconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MqConfig {

    @Bean
    public Exchange customeExchange(){
        Map<String,Object> args = new HashMap<>();
        args.put("x-delayed-type","direct");
        return new CustomExchange("delayed_exchange", "x-delayed-message",true,true,args);
    }

    @Bean
    public Queue delayedQueue(){
        return new Queue("delayed_queue",true,false,true);
    }

    @Bean
    public Binding bindingQueue(Queue delayedQueue, Exchange customeExchange){
        return BindingBuilder
                .bind(delayedQueue)
                .to(customeExchange)
                .with("info")
                .noargs();
    }
}
