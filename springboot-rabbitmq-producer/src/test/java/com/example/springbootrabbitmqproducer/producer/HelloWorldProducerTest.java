package com.example.springbootrabbitmqproducer.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloWorldProducerTest {

    @Autowired
    private HelloWorldProducer producer;

    @Test
    public void publishMessage() {
        producer.publishMessage("hello queue");
    }
}