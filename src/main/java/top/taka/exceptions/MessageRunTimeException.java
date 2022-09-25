package top.taka.exceptions;

/**
 * 定义项目运行时的异常
 */
public class MessageRunTimeException extends RuntimeException {

    public MessageRunTimeException() {
    }

    public MessageRunTimeException(String message) {
        super(message);
    }

    public MessageRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageRunTimeException(Throwable cause) {
        super(cause);
    }

    public MessageRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
