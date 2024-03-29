package com.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

        //队列名称
        private final static String QUEUE_NAME = "orderQueue";

        public static void main(String[] argv) throws java.io.IOException {
            /**
             * 创建连接连接到MabbitMQ
             */
            ConnectionFactory factory = new ConnectionFactory();
            //设置MabbitMQ所在主机ip或者主机名
            factory.setHost("144.34.162.179");
            factory.setPort(5672);
            factory.setUsername("admin");
            factory.setPassword("admin");
            //创建一个连接
            Connection connection = factory.newConnection();
            //创建一个频道
            Channel channel = connection.createChannel();
            //指定一个队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for (int i = 0; i < 10; i++) {

                //发送的消息
                String message = "hello world!"+i;
                //往队列中发出一条消息
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("Sent '" + message + "'");
            }
            //关闭频道和连接
            channel.close();
            connection.close();
        }
}
