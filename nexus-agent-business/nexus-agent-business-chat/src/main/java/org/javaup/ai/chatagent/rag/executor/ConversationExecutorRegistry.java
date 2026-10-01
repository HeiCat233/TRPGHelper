package org.javaup.ai.chatagent.rag.executor;

import org.javaup.ai.chatagent.rag.model.ExecutionMode;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 对话执行器注册表
 * @author: 阿星不是程序员
 **/

@Component
public class ConversationExecutorRegistry {

    private final Map<ExecutionMode, ConversationExecutor> executorMap = new EnumMap<>(ExecutionMode.class);

    public ConversationExecutorRegistry(List<ConversationExecutor> executors) {
        for (ConversationExecutor executor : executors) {
            executorMap.put(executor.mode(), executor);
        }
    }

    public ConversationExecutor get(ExecutionMode mode) {
        ConversationExecutor executor = executorMap.get(mode);
        if (executor == null) {
            throw new IllegalStateException("未找到执行模式对应的执行器: " + mode);
        }
        return executor;
    }
}
