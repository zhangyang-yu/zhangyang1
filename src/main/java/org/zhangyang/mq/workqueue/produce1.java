package org.zhangyang.mq.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.zhangyang.mq.produce.util.GetConnction;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列
 * 同一个队列的数据，有多个消费端获取，被获取的数据会从消息队列中情况
 */
public class produce1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = GetConnction.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("workqueue", true, false, false, null);
        for (int i = 0; i < 50; i++) {
            String message = "第" + i + "次";
            channel.basicPublish("", "workqueue", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        }
        channel.close();
        connection.close();
    }
}
