package org.zhangyang.mq.topic;

import com.rabbitmq.client.*;
import com.rabbitmq.client.impl.nio.SslEngineByteBufferOutputStream;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import org.zhangyang.mq.produce.util.GetConnction;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class cumsumer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = GetConnction.getConnection();

        Channel channel = connection.createChannel();

        channel.basicQos(1);

        channel.queueDeclare("topicqueue1",true,false,false,null);

        channel.queueBind("topicqueue1","topic_execture","user.#");

        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println( "cumsumer1:" +new String(body));
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        };

        channel.basicConsume("topicqueue1",false,consumer);


    }
}
