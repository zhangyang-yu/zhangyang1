package org.zhangyang.mq.produce;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.zhangyang.mq.produce.util.GetConnction;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * rubbitMQ消息队列的生产者，服务生产者把数据发送到指定的队列中，这样服务消费者监测消息队列，发现信息就可以获取到
 */
public class mqpro1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1：创建连接工厂
        //2：设置连接参数
        //3：获取连接
        //4：创建连接通道
        //5：在连接通道中创建队列
        //6：把数据存储到连接通道的队列上
        //7：关闭连接通道和连接

        Connection connection= GetConnction.getConnection();
        Channel channel = connection.createChannel();
        //(创建）队列 /***
        // 参数1：队列名称 *
        // 参数2：是否定义持久化队列 *
        // 参数3：是否独占本次连接 *
        // 参数4：是否在不使用的时候自动删除队列
        // * 参数5：队列其它参数 */

        channel.queueDeclare("simpleMq", true, false, false, null);

        /*** 参数1：交换机名称，如果没有指定则使用默认Default Exchage *
         *  参数2：路由key,简单模式可以传递队列名称
         *  参数3：消息其它属性 *
         *  参数4：消息内容 */
        String message="zhangyang";
        channel.basicPublish("","simpleMq",null,message.getBytes());
        channel.close();;
        connection.close();
    }
}
