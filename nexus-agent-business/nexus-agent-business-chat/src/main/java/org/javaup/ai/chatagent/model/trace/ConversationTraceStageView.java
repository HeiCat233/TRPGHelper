package org.javaup.ai.chatagent.model.trace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 视图对象
 * @author: 阿星不是程序员
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationTraceStageView {

    private long stageId;

    private String traceId;

    private String stageCode;

    private String stageName;

    private Integer stageOrder;

    private Integer stageLevel;

    private Long parentStageId;

    private String executionMode;

    private String stageState;

    private Instant startTime;

    private Instant endTime;

    private Long durationMs;

    private String summaryText;

    private String errorMessage;

    private Map<String, Object> snapshot;
}
