package org.javaup.ai.chatagent.rag.model;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 导航层输出的结构范围模式
 * @author: 阿星不是程序员
 **/

public enum NavigationScopeMode {
    NONE,
    SOFT,
    HARD_SECTION,
    HARD_ITEM,
    HARD_PARENT_WITH_SIBLINGS
}
