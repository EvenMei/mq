package com.meiyukai.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author meiyukai
 */
public class ConnectionUtils {
    public static ConnectionFactory connectionFactory = null;
    public static   Connection connection = null;
    static{
         connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("rabbit");
        connectionFactory.setPassword("rabbit");
        connectionFactory.setVirtualHost("/ems");
    }
    
    public static Connection getConnection(){
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Channel  channel){
        if(channel != null && connection != null){
            try {
                channel.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
