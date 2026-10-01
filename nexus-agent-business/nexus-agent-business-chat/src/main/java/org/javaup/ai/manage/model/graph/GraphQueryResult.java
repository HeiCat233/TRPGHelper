package org.javaup.ai.manage.model.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 结构图查询执行结果
 * @author: 阿星不是程序员
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraphQueryResult {

    private GraphSection targetSection;

    private GraphSection parentSection;

    private GraphSection previousSibling;

    private GraphSection nextSibling;

    private GraphItem targetItem;

    @Builder.Default
    private List<GraphSection> children = new ArrayList<>();

    @Builder.Default
    private List<GraphItem> matchedItems = new ArrayList<>();

    @Builder.Default
    private List<GraphItem> allItems = new ArrayList<>();
}
