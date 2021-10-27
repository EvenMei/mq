package com.example.springbootrabbitmqproducer.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DelayedProducerTest {

    @Resource
    private DelayedProducer producer;

    @Test
    public void publishDelayedMessage() {
        producer.publishDelayedMessage(" DELAYED MESSAGE TIME : ");
    }

}