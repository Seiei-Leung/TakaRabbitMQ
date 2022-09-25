package top.taka.pojo;

import top.taka.enums.MESSAGETYPE;

import java.io.Serializable;
import java.util.Map;

// 使用 RabbitMQ 作为消息体时，需要实现序列化接口（idea 点击类名，快捷键 alt+enter 自动生成 serialVersionUID）
public class Message implements Serializable{

    private static final long serialVersionUID = 7530076409088984334L;

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

    public Message(String exchange, String routingKey, int messageType, String messageId, Object content, Map<String, Object> attributes, long delayMills) {
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.messageType = messageType;
        this.messageId = messageId;
        this.content = content;
        this.attributes = attributes;
        this.delayMills = delayMills;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public long getDelayMills() {
        return delayMills;
    }

    public void setDelayMills(long delayMills) {
        this.delayMills = delayMills;
    }
}
