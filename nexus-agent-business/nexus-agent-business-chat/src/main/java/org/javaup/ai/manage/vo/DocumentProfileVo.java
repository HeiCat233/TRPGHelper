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
public class DocumentProfileVo {

    private String documentId;

    private String documentSummary;

    private String documentType;

    private String coreTopics;

    private String exampleQuestions;

    private String graphFriendly;

    private String supportsGraphOutline;

    private String supportsItemLookup;

    private String supportsGraphAssist;

    private String profileSource;

    private String profileStatus;

    private String errorMsg;
}
