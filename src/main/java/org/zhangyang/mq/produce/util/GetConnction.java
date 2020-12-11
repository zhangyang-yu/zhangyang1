package org.zhangyang.mq.produce.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class GetConnction {
    public  static Connection getConnection() throws IOException, TimeoutException {
        //1：创建连接工厂
        //2：设置连接参数
        //3：获取连接
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("192.168.127.128");
        connectionFactory.setPort(5672);
        connectionFactory.setPassword("guest");
        connectionFactory.setUsername("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        return  connection;

    }
}
