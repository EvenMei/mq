package com.example.springbootrabbitmqproducer.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TopicProducerTest {

    @Resource
    private TopicProducer topicProducer;

    @Test
    public void publishMessage() {
    topicProducer.publishMessage("COM.MEIYUKAI.TERMINUS");
    }
}