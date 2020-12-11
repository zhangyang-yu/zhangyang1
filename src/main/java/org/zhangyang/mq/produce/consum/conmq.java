package org.zhangyang.mq.produce.consum;

import com.rabbitmq.client.*;
import org.zhangyang.mq.produce.util.GetConnction;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class conmq {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1:获取连接
        //2：创建通道
        //3：开始监听队列
        Connection connection = GetConnction.getConnection();

        Channel channel = connection.createChannel();

        //烤监听队列三个参数
        //1： 监听队列的名称
        //2： 是否自动返回确认接收消息
        //3： 消费者
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者标识:"+consumerTag);
                System.out.println("交换机:"+envelope.getExchange());
                System.out.println("路由key："+envelope.getRoutingKey());
                System.out.println("消息标识"+envelope.getDeliveryTag());
                System.out.println("服务方提供的信息:"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),true);//通知rubbitMQ消费端已经从消息队列中获取到数据了

            }
        };
        channel.basicConsume("simpleMq",false,consumer);

    }
}
