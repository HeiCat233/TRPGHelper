package org.javaup.ai.manage.model.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 目标编号项及其所在章节上下文
 * @author: 阿星不是程序员
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraphItemWithContext {

    private GraphSection section;

    private GraphItem item;

    @Builder.Default
    private List<GraphItem> siblingItems = new ArrayList<>();
}
