package org.zhangyang.mq.radio;

import com.rabbitmq.client.*;
import org.zhangyang.mq.produce.util.GetConnction;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class cunsumer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = GetConnction.getConnection();

        Channel channel = connection.createChannel();

        channel.basicQos(1);

        channel.queueDeclare("myqueue1",true,false,false,null);

        channel.queueBind("myqueue1","myExchange","");

        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        };
        channel.basicConsume("myqueue1",false,consumer);

    }
}
