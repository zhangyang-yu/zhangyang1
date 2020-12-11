package org.zhangyang.mq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.zhangyang.mq.produce.util.GetConnction;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class produce {
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = GetConnction.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare("routing_exchange","direct");

        String message="这个是路由定向发送数据协议";

        channel.basicPublish("routing_exchange","Aji",null,message.getBytes());

        channel.basicPublish("routing_exchange","Bji",null,message.getBytes());

        channel.basicPublish("routing_exchange","Cji",null,message.getBytes());

        channel.close();

        connection.close();


    }
}
