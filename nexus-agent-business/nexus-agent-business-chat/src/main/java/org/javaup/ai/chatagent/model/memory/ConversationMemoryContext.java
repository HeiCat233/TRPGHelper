package org.javaup.ai.chatagent.model.memory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 编排阶段真正使用的会话记忆上下文
 * @author: 阿星不是程序员
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationMemoryContext {

    private String assembledHistory;

    private String longTermSummary;

    private String recentTranscript;

    private String answerRecentTranscript;

    private ConversationSummaryPayload summaryPayload;

    private Long coveredExchangeId;

    private Integer coveredExchangeCount;

    private Integer compressionCount;

    private boolean compressionApplied;
}
