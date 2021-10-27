package com.meiyukai.springbootrabbitmqconsumer.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class WorkQueueConsumer {


    @RabbitListener(queuesToDeclare = @Queue(value = "work_queue")/*, ackMode = "manual"*/)
    public void consumeMessageFaster(String message, Channel channel, @Header(name = AmqpHeaders.DELIVERY_TAG) Long tag) throws IOException {
        System.out.println("work queue GET message : " + message);
        channel.basicAck(tag, false);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "work_queue")/*, ackMode = "manual"*/)
    public void consumeMessageSlower(String message, Channel channel, @Header(name = AmqpHeaders.DELIVERY_TAG) Long tag) throws Exception{
        System.out.println("work queue 2 GET message : " + message);
        channel.basicAck(tag, false);
        TimeUnit.SECONDS.sleep(2);
    }


}
