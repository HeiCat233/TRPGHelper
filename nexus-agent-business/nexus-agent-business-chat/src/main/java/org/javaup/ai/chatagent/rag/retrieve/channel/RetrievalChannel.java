package org.javaup.ai.chatagent.rag.retrieve.channel;

import org.javaup.ai.chatagent.rag.model.ConversationExecutionPlan;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 检索通道抽象
 * @author: 阿星不是程序员
 **/

public interface RetrievalChannel {

    String channelName();

    boolean supports(ConversationExecutionPlan plan);

    RetrievalChannelResult retrieve(String subQuestion, ConversationExecutionPlan plan);
}
