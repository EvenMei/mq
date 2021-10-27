package com.example.springbootrabbitmqproducer.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class DirectProducerTest {

    @Autowired
    private DirectProducer directProducer;

    @Test
    public void publishMessage() {
        directProducer.publishMessage("ERROR MESSAGE");
    }
}