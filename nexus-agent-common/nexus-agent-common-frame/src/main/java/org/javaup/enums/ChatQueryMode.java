package org.javaup.enums;

import lombok.Getter;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 枚举定义
 * @author: 阿星不是程序员
 **/

@Getter
public enum ChatQueryMode {

    /**
     * 当前文档问答
     * */
    DOCUMENT(1, "当前文档问答"),
    
    /**
     * 自动知识问答
     * */
    AUTO_DOCUMENT(3, "自动知识问答"),
    
    /**
     * 开放式提问
     * */
    OPEN_CHAT(2, "开放式提问");

    private final int code;
    private final String label;

    ChatQueryMode(int code, String label) {
        this.code = code;
        this.label = label;
    }
    
    public static ChatQueryMode fromCode(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("提问模式 code 不能为空");
        }
        for (ChatQueryMode mode : values()) {
            if (mode.code == code) {
                return mode;
            }
        }
        throw new IllegalArgumentException("未知的提问模式 code: " + code);
    }
}
