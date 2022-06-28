package com.mabushizai.maibudu.config;

/**
 * 业务异常
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/27
 */
public class MaibuduException extends RuntimeException {

    public MaibuduException() {
    }

    public MaibuduException(String message) {
        super(message);
    }

    public MaibuduException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaibuduException(Throwable cause) {
        super(cause);
    }

    public MaibuduException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
