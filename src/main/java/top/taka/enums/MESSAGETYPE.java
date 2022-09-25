package top.taka.enums;

public enum MESSAGETYPE {

    // 迅速消息：不需要保障消息的可靠性，也不需要做 confirm 确认
    RAPID(0, "迅速消息"),
    // 确认消息：不需要保障消息的可靠性，但会做消息的 confirm 确认
    CONFIRM(1, "确认消息"),
    // 可靠性消息：一定要保障消息的 100% 可靠性投递，不允许有任何消息的丢失（保障数据库和所发的消息是原子性的）
    RELIANT(2, "可靠性消息");

    public final int type;
    public final String value;

    MESSAGETYPE(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
