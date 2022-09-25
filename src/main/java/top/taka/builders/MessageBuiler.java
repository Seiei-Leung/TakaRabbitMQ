package top.taka.builders;

import top.taka.enums.MESSAGETYPE;
import top.taka.exceptions.MessageRunTimeException;
import top.taka.pojo.Message;

import java.util.Map;
import java.util.UUID;

/**
 * Message 对象，建造者模式
 * 建造者模式默认的成员变量与想要建造返回的对象要一致
 */
public class MessageBuiler {

    // exchange 的名字
    private String exchange;

    // 路由名字
    private String routingKey;

    // 消息类型
    private int messageType = MESSAGETYPE.RAPID.type;

    // 消息 ID
    private String messageId;

    // 消息内容
    private Object content;

    // 附加属性
    private Map<String, Object> attributes;

    // 延迟时间
    private long delayMills;

    /**
     * 隐藏构造函数
     */
    private MessageBuiler() {}

    /**
     * 提供接口，让用户创建 MessageBuilder
     * @return
     */
    public MessageBuiler create() {
        return new MessageBuiler();
    }

    public MessageBuiler withExchange(String exchange) {
        this.exchange = exchange;
        return this;
    }

    public MessageBuiler withRoutingKey(String routingKey) {
        this.routingKey = routingKey;
        return this;
    }

    public MessageBuiler withMessageType(int messageType) {
        this.messageType = messageType;
        return this;
    }

    public MessageBuiler withMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public MessageBuiler withContent(Object content) {
        this.content = content;
        return this;
    }

    public MessageBuiler withAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public MessageBuiler withDelayMills(long delayMills) {
        this.delayMills = delayMills;
        return this;
    }

    public Message build() {
        if (messageId == null) {
            messageId = UUID.randomUUID().toString();
        }
        if (exchange == null) {
            throw new MessageRunTimeException("exchange 不能为 null");
        }
        return new Message(exchange, routingKey, messageType, messageId, content, attributes, delayMills);
    }


}
