package top.taka.producer;

import top.taka.api.MessageClient;
import top.taka.api.SendCallBack;
import top.taka.pojo.Message;

import java.util.List;

/**
 * 最外层用户调用消息发送的接口实现类
 */
public class MessageClientImpl implements MessageClient {

    @Override
    public void send(Message message) {

    }

    @Override
    public void send(List<Message> messageList) {

    }

    @Override
    public void send(Message message, SendCallBack sendCallBack) {

    }
}
