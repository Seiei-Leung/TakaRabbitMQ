package top.taka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import top.taka.api.MessageBroker;
import top.taka.enums.MESSAGETYPE;
import top.taka.pojo.Message;

/**
 * 根据消息类型不同而执行发送消息逻辑的接口实现类
 */
public class MessageBrokerImpl implements MessageBroker {

    private static final Logger logger = LoggerFactory.getLogger(MessageBrokerImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 迅速消息
    @Override
    public void rapidSend(Message message) {
        sendKernel(message);
    }

    // 确认消息
    @Override
    public void confirmSend(Message message) {
        sendKernel(message);
    }

    // 可靠性消息
    @Override
    public void reliantSend(Message message) {

    }

    @Override
    public void sendMessages() {

    }

    /**
     * 发送消息的核心方法，使用 Spring 自动装配进来的 RabbitTemplate 发送消息
     * @param message
     */
    private void sendKernel(Message message) {
        // 发送消息的唯一 ID
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(String.format("%s#%s", message.getMessageId(), System.currentTimeMillis()));
        String routingKey = message.getRoutingKey();
        String exchange = message.getExchange();
        int messageType = message.getMessageType();
        // 只要不是迅速消息，就需要添加确认消息应答回调函数
        if (messageType != MESSAGETYPE.RAPID.type) {
            rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
                /**
                 * 重写方法
                 * @param correlationData 可以获取 发送消息时设置的唯一 ID
                 * @param ack 表示消息是否到达了交换机，交换机是否确认收到消息（MQ 集群数据复制发生错误或MQ 队列满了）
                 *            如果是路由 key 没有对应消息队列，还是会触发这个回调方法，同时 ack 也是会返回 true
                 *            如果是绑定 exchange 绑定错了，MQ 里没有该 exchange，会触发这个回调方法，同时 ack 返回 false
                 *            如果是宕机或者网络问题，会直接接收不了消息，即不会触发该消息
                 *
                 * @param s 存储的是没有到达交换机的原因
                 */
                @Override
                public void confirm(CorrelationData correlationData, boolean ack, String s) {
                    String correlationDataId = correlationData.getId();
                    String msgId = correlationDataId.split("#")[0];
                    logger.info("发送消息的唯一 ID：" + msgId);
                    if (ack) {
                        // todo 如果发送的是可靠性消息，则需要更新数据库的消息状态
                        if (message.getMessageType() == MESSAGETYPE.RELIANT.type) {

                        }
                        logger.info("消息到达到交换机");
                    } else {
                        logger.error("消息没有到达到交换机，原因是：" + s);
                    }
                }
            });
        }
        rabbitTemplate.convertAndSend(
                exchange,
                routingKey,
                message,
                correlationData
        );
    }
}
