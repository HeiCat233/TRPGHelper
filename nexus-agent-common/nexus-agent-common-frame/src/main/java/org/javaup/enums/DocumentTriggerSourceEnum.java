package org.javaup.enums;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 枚举定义
 * @author: 阿星不是程序员
 **/

public enum DocumentTriggerSourceEnum {
    SYSTEM(1, "系统自动"),
    USER(2, "用户手动");

    private final Integer code;

    private final String msg;

    DocumentTriggerSourceEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public static DocumentTriggerSourceEnum getRc(Integer code) {
        for (DocumentTriggerSourceEnum item : DocumentTriggerSourceEnum.values()) {
            if (item.code.intValue() == code.intValue()) {
                return item;
            }
        }
        return null;
    }
}
