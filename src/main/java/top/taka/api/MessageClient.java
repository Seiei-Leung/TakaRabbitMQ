package top.taka.api;

import top.taka.pojo.Message;

import java.util.List;

/**
 * 最外层用户调用消息发送的接口抽象
 */
public interface MessageClient {

    /**
     * 发送消息
     * @param message
     */
    void send(Message message);

    /**
     * 批量发送消息
     * @param messageList
     */
    void send(List<Message> messageList);

    /**
     * 发送消息后回调
     * @param message
     * @param sendCallBack
     */
    void send(Message message, SendCallBack sendCallBack);
}
