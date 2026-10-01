package org.javaup.enums;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 枚举定义
 * @author: 阿星不是程序员
 **/

public enum DocumentStrategyPipelineTypeEnum {
    PARENT("PARENT", "父块流水线"),
    CHILD("CHILD", "子块流水线");

    private final String code;

    private final String msg;

    DocumentStrategyPipelineTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public static DocumentStrategyPipelineTypeEnum getRc(String code) {
        if (code == null) {
            return null;
        }
        for (DocumentStrategyPipelineTypeEnum item : DocumentStrategyPipelineTypeEnum.values()) {
            if (item.code.equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }
}
