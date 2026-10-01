package org.javaup.ai.manage.service;

import java.util.List;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 服务层
 * @author: 阿星不是程序员
 **/

public interface KnowledgeRouteIndexService {

    void refreshIfNeeded();

    List<RouteLexicalHit> search(String routingText, String entityType, int size);

    void deleteDocumentRoute(Long documentId);

    record RouteLexicalHit(
        String routeId,
        String entityCode,
        String entityType,
        Long documentId,
        String scopeCode,
        String topicCode,
        String documentName,
        double score
    ) {
    }
}
