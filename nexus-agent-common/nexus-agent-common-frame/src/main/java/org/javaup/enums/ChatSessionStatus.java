package org.javaup.enums;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 枚举定义
 * @author: 阿星不是程序员
 **/

public enum ChatSessionStatus {

    IDLE(1, "空闲"),

    RUNNING(2, "进行中");

    private final int code;
    private final String desc;

    ChatSessionStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ChatSessionStatus fromCode(Integer code) {
        if (code == null) {
            return IDLE;
        }
        for (ChatSessionStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的会话状态 code: " + code);
    }

    public static boolean isRunning(Integer code) {
        return RUNNING.code == (code == null ? IDLE.code : code);
    }
}
