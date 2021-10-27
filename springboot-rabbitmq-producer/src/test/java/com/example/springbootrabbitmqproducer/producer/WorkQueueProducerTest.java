package com.example.springbootrabbitmqproducer.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkQueueProducerTest {

    @Autowired
    private WorkQueueProducer producer;

    @Test
    public void publishMessage() {
        producer.publishMessage();
    }
}