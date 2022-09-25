package top.taka.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "broker_message")
public class BrokerMessage implements Serializable {

    private static final long serialVersionUID = -4950800689324666210L;
    /**
     * 消息唯一 ID
     */
    @Id
    @Column(name = "message_id")
    private String messageId;

    /**
     * 消息内容，JSON格式
     */
    private String message;

    /**
     * 尝试次数
     */
    @Column(name = "try_count")
    private Integer tryCount;

    /**
     * 消息状态
     */
    private String status;

    /**
     * 下次重复发送消息时间
     */
    @Column(name = "next_retry")
    private Date nextRetry;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取消息唯一 ID
     *
     * @return message_id - 消息唯一 ID
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * 设置消息唯一 ID
     *
     * @param messageId 消息唯一 ID
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取消息内容，JSON格式
     *
     * @return message - 消息内容，JSON格式
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置消息内容，JSON格式
     *
     * @param message 消息内容，JSON格式
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取尝试次数
     *
     * @return try_count - 尝试次数
     */
    public Integer getTryCount() {
        return tryCount;
    }

    /**
     * 设置尝试次数
     *
     * @param tryCount 尝试次数
     */
    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    /**
     * 获取消息状态
     *
     * @return status - 消息状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置消息状态
     *
     * @param status 消息状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取下次重复发送消息时间
     *
     * @return next_retry - 下次重复发送消息时间
     */
    public Date getNextRetry() {
        return nextRetry;
    }

    /**
     * 设置下次重复发送消息时间
     *
     * @param nextRetry 下次重复发送消息时间
     */
    public void setNextRetry(Date nextRetry) {
        this.nextRetry = nextRetry;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}