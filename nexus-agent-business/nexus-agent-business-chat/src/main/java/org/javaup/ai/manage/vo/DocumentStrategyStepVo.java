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
public class DocumentStrategyStepVo {

    private Integer stepNo;

    private String pipelineType;

    private String pipelineTypeName;

    private Integer strategyType;

    private String strategyName;

    private Integer strategyRole;

    private String strategyRoleName;

    private Integer sourceType;

    private String sourceTypeName;

    private Integer executeStatus;

    private String executeStatusName;

    private String recommendReason;
}
