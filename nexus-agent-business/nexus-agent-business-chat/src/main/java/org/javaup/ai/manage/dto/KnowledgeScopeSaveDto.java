package org.javaup.ai.manage.dto;

import lombok.Data;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 数据传输对象
 * @author: 阿星不是程序员
 **/
@Data
public class KnowledgeScopeSaveDto {

    private String id;

    private String scopeCode;

    private String scopeName;

    private String parentScopeCode;

    private String description;

    private String aliases;

    private String examples;

    private String sortOrder;

    private String operatorId;
}
