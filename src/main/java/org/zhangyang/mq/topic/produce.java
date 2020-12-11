package org.zhangyang.mq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.zhangyang.mq.produce.util.GetConnction;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class produce {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = GetConnction.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare("topic_execture","topic",true);

        String message="这个是topic模糊匹配";

        channel.basicPublish("topic_execture","user.message",true,false,null,message.getBytes());

        channel.basicPublish("topic_execture","user.message.zhangyang",true,false,null,message.getBytes());

        channel.basicPublish("topic_execture","ss.person",true,false,null,message.getBytes());

        channel.basicPublish("topic_execture","aaa.bbb",true,false,null,message.getBytes());
        channel.close();

        connection.close();

    }
}
