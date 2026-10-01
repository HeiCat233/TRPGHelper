package org.javaup.ai.chatagent.vo;

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
public class ConversationResetVo {

    private String conversationId;

    private boolean stoppedRunningTask;

    private int removedDialogueCount;

    private int removedExchangeCount;

    private int removedCheckpointCount;

    private String message;
}
