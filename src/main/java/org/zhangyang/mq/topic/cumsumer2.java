package org.zhangyang.mq.topic;

import com.rabbitmq.client.*;
import org.zhangyang.mq.produce.util.GetConnction;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class cumsumer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = GetConnction.getConnection();

        Channel channel = connection.createChannel();

        channel.basicQos(1);

        channel.queueDeclare("topicqueue",true,false,false,null);

        channel.queueBind("topicqueue","topic_execture","user.#");

        channel.queueBind("topicqueue","topic_execture","#.person");

        channel.queueBind("topicqueue","topic_execture","aaa.*");

        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println( "cumsumer2:" +new String(body));
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        };

        channel.basicConsume("topicqueue",false,consumer);


    }
}
