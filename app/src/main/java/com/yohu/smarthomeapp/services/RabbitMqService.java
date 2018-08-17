package com.yohu.smarthomeapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.yohu.smarthomeapp.base.Constance;
import com.yohu.smarthomeapp.services.message.RabbitMqMessage;
import com.yohu.smarthomeapp.utils.show.L;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqService extends Service {
    private ConnectionFactory factory = new ConnectionFactory();

    @Override
    public void onCreate() {
        super.onCreate();
        setUpFactory();
        new Thread(new Runnable() {
            @Override
            public void run() {
                subscribe();
            }
        }).start();

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void subscribe() {
        try {
            Connection connection = factory.newConnection();

            Channel channel = connection.createChannel();
            channel.basicQos(1);

            // 随机命名一个队列名称
            String queueName = System.currentTimeMillis() + "smart_home_app";
            // 声明交换机类型
            channel.exchangeDeclare(Constance.MQ_EXCHANGE, "direct", true);
            // 声明队列（持久的、非独占的、连接断开后队列会自动删除）
            AMQP.Queue.DeclareOk q = channel.queueDeclare(queueName, true, false, true, null);// 声明共享队列
            // 根据路由键将队列绑定到交换机上（需要知道交换机名称和路由键名称）
            channel.queueBind(q.getQueue(), Constance.MQ_EXCHANGE, Constance.MQ_ROUTINGKEY);
            // 创建消费者获取rabbitMQ上的消息。每当获取到一条消息后，就会回调handleDelivery（）方法，该方法可以获取到消息数据并进行相应处理
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);
                    // 通过geiBody方法获取消息中的数据
                    String message = new String(body);
                    L.d(message);

                    Gson gson = new Gson();
                    RabbitMqMessage rabbitMqMessage = gson.fromJson(message, RabbitMqMessage.class);

                    EventBus.getDefault().post(rabbitMqMessage);
                }
            };
            channel.basicConsume(q.getQueue(), true, consumer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void setUpFactory() {
        factory.setHost(Constance.MQ_HOST);
        factory.setPort(Constance.MQ_PORT);
        factory.setUsername(Constance.MQ_USERNAME);// 用户名
        factory.setPassword(Constance.MQ_PASSWORD);// 密码
        factory.setAutomaticRecoveryEnabled(true);// 设置连接恢复
    }
}
