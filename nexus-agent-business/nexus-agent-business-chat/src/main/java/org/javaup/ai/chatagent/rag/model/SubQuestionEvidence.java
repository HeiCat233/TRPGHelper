package org.javaup.ai.chatagent.rag.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javaup.ai.chatagent.model.SearchReference;
import org.springframework.ai.document.Document;

import java.util.List;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 单个子问题的证据容器
 * @author: 阿星不是程序员
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubQuestionEvidence {

    private int subQuestionIndex;

    private String subQuestion;

    private List<Document> documents;

    private List<SearchReference> references;

    private List<SubQuestionChannelTrace> channelTraces;

    private Integer fusedCandidateCount;

    private Integer parentCandidateCount;

    private Integer rerankedCandidateCount;
}
