package org.javaup.ai.manage.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 数据传输对象
 * @author: 阿星不是程序员
 **/

@Data
public class DocumentStrategyStepItemDto {

    private Integer stepNo;

    @NotNull(message = "策略类型不能为空")
    private Integer strategyType;
}
