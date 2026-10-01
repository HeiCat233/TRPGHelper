package org.javaup.ai.chatagent.rag.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 回答阶段最终使用的历史上下文
 * @author: 阿星不是程序员
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerHistoryContext {

    private String renderedText;

    private String structuredContext;

    private String recentContext;

    private boolean followUpQuestion;

    private Integer totalBudget;

    private Integer recentBudget;

    private Integer structuredBudget;

    public boolean isEmpty() {
        return renderedText == null || renderedText.isBlank();
    }
}
