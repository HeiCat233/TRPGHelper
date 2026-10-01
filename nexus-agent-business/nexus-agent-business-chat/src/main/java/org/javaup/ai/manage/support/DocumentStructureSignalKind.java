package org.javaup.ai.manage.support;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 枚举定义
 * @author: 阿星不是程序员
 **/

public enum DocumentStructureSignalKind {
    DOCUMENT_TITLE,
    HEADING,
    HEADING_CANDIDATE,
    STEP_ITEM,
    LIST_ITEM,
    TABLE_ROW,
    QUOTE,
    BODY,
    BLANK,
    NOISE
}
