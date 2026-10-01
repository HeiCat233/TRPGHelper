package org.javaup.ai.manage.support;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 支撑组件
 * @author: 阿星不是程序员
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentStructureSignal {

    private int lineNo;

    private String rawText;

    private String normalizedText;

    private DocumentStructureSignalKind kind;

    private String nodeCode;

    private String title;

    private Integer levelHint;

    private Integer indentLevel;

    private Integer itemIndex;

    @Builder.Default
    private List<Integer> numericPath = new ArrayList<>();

    @Builder.Default
    private List<String> reasons = new ArrayList<>();

    private double confidence;

    public boolean isHeadingLike() {
        return kind == DocumentStructureSignalKind.HEADING
            || kind == DocumentStructureSignalKind.HEADING_CANDIDATE;
    }

    public boolean isListLike() {
        return kind == DocumentStructureSignalKind.STEP_ITEM
            || kind == DocumentStructureSignalKind.LIST_ITEM;
    }

    public boolean isAmbiguous() {
        return kind == DocumentStructureSignalKind.HEADING_CANDIDATE;
    }
}
