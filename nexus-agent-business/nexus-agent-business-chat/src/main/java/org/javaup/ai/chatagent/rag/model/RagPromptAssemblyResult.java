package org.javaup.ai.chatagent.rag.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: RAG Prompt 装配结果
 * @author: 阿星不是程序员
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RagPromptAssemblyResult {

    private String systemPrompt;

    private String userPrompt;

    private int totalBudget;

    private int perSubQuestionBudget;

    private int renderedReferenceCount;

    private int omittedReferenceCount;

    private List<String> renderedReferenceDetails;

    private List<String> omittedReferenceDetails;
}
