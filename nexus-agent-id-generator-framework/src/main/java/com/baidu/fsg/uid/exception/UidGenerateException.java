package com.baidu.fsg.uid.exception;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 异常类
 * @author: 阿星不是程序员
 **/

public class UidGenerateException extends RuntimeException {

    private static final long serialVersionUID = -27048199131316992L;

    public UidGenerateException() {
        super();
    }

    public UidGenerateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UidGenerateException(String message) {
        super(message);
    }

    public UidGenerateException(String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
    }

    public UidGenerateException(Throwable cause) {
        super(cause);
    }

}
