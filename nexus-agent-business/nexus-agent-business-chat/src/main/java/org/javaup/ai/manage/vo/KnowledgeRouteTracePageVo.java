package org.javaup.ai.manage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 视图对象
 * @author: 阿星不是程序员
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeRouteTracePageVo {

    private String pageNo;

    private String pageSize;

    private String totalSize;

    private String totalPages;

    private List<KnowledgeRouteTraceItemVo> records;
}
