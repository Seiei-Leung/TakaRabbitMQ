package top.taka.api;

import top.taka.pojo.Message;

/**
 * 根据消息类型不同而执行发送消息逻辑的接口
 */
public interface MessageBroker {

    // 迅速消息
    void rapidSend(Message message);

    // 确认消息
    void confirmSend(Message message);

    // 可靠性消息
    void reliantSend(Message message);

    // 批量发送消息
    void sendMessages();
}
