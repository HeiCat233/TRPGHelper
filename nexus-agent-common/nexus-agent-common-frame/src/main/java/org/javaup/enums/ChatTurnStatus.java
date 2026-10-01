package org.javaup.enums;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 枚举定义
 * @author: 阿星不是程序员
 **/

public enum ChatTurnStatus {

    RUNNING(1, "进行中"),

    COMPLETED(2, "已完成"),

    FAILED(3, "失败"),

    STOPPED(4, "已停止");

    private final int code;
    private final String desc;

    ChatTurnStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ChatTurnStatus fromCode(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("轮次状态 code 不能为空");
        }
        for (ChatTurnStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的轮次状态 code: " + code);
    }
}
