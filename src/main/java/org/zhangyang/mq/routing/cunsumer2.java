package org.zhangyang.mq.routing;

import com.rabbitmq.client.*;
import org.zhangyang.mq.produce.util.GetConnction;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class cunsumer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = GetConnction.getConnection();

        Channel channel = connection.createChannel();

        channel.basicQos(1);

        channel.queueDeclare("routingqueue2",true,false,false,null);

        channel.queueBind("routingqueue2","routing_exchange","Aji");
        channel.queueBind("routingqueue2","routing_exchange","Bji");
        channel.queueBind("routingqueue2","routing_exchange","Cji");


        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("这个是cumsumer2"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        };
        channel.basicConsume("routingqueue2",false,consumer);

    }

}
