package org.javaup.ai.manage.dto;

import lombok.Data;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 数据传输对象
 * @author: 阿星不是程序员
 **/

@Data
public class DocumentUploadDto {

    private String documentName;

    private String operatorId;

    private String knowledgeScopeCode;

    private String knowledgeScopeName;

    private String businessCategory;

    private String documentTags;
}
