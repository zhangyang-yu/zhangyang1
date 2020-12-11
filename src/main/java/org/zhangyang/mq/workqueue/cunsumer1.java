package org.zhangyang.mq.workqueue;

import com.rabbitmq.client.*;
import org.zhangyang.mq.produce.util.GetConnction;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class cunsumer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = GetConnction.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);//设置每一次从消息队列中获取一条数据，处理结束返回确认信息才处理下一条数据
        //不设置这个的消费端都默认平均获取消息队列的数据，设置这个就可以实现能者多劳
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
               channel.basicAck(envelope.getDeliveryTag(),true);
            }
        };

        channel.basicConsume("workqueue",false,consumer);

    }
}
