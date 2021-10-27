package com.meiyukai.producer;

import com.meiyukai.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author meiyukai
 */
public class WorkQueueProducer {
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("workqueue",true,false,true,null);
        for(int i = 0 ; i < 10 ; i++ ){
            channel.basicPublish("","workqueue",null,(i+" workqueue message ").getBytes());
        }
    }
}
