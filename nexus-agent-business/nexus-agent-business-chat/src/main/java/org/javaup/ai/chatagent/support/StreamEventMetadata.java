package org.javaup.ai.chatagent.support;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 支撑组件
 * @author: 阿星不是程序员
 **/

public record StreamEventMetadata(
    String conversationId,
    Long exchangeId
) {
}
