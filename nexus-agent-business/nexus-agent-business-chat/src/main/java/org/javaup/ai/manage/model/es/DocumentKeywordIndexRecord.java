package org.javaup.ai.manage.model.es;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: Elasticsearch 关键词索引文档
 * @author: 阿星不是程序员
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentKeywordIndexRecord {

    private String chunkId;

    private Long documentId;

    private Long taskId;

    private Long parentBlockId;

    private Integer chunkNo;

    private String documentName;

    private String sectionPath;

    private Long structureNodeId;

    private Integer structureNodeType;

    private String canonicalPath;

    private Integer itemIndex;

    private String knowledgeScopeCode;

    private String knowledgeScopeName;

    private String businessCategory;

    @Builder.Default
    private List<String> documentTags = new ArrayList<>();

    private String chunkText;
}
