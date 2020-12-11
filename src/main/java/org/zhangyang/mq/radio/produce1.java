package org.zhangyang.mq.radio;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.zhangyang.mq.produce.util.GetConnction;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeoutException;

/**
 广播代理模式：一个消息提供者，多个消费提供者都可以获取到消息提供者提供的数据
 服务提供端把数据发送给交换机
 交换机不会保存数据，他会立即把数据发送给绑定这个交换机的队列（没有队列接收数据机会丢失）


 持久化：
 队列的持久化
 消息的持久化
 交换机的持久化
 */
public class produce1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = GetConnction.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare("myExchange","fanout",true);//设置交换价

        String meessage="这个是广播订阅模式";

        channel.basicPublish("myExchange","", MessageProperties.PERSISTENT_TEXT_PLAIN,meessage.getBytes());

        channel.close();

        connection.close();
    }
}
