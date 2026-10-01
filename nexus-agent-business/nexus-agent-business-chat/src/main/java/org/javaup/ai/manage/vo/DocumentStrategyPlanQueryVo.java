package org.javaup.ai.manage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 视图对象
 * @author: 阿星不是程序员
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentStrategyPlanQueryVo {

    private Long documentId;

    private String documentName;

    private Integer parseStatus;

    private String parseStatusName;

    private Integer strategyStatus;

    private String strategyStatusName;

    private Integer indexStatus;

    private String indexStatusName;

    private String parseErrorMsg;

    private Boolean planReady;

    private DocumentStrategyPlanVo plan;
}
