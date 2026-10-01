package org.javaup.ai.chatagent.service;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 服务层
 * @author: 阿星不是程序员
 **/
@Component
public class ChatRuntimeRegistry {

    private final ConcurrentMap<String, TaskInfo> taskMap = new ConcurrentHashMap<>();

    public boolean register(TaskInfo taskInfo) {
        return taskMap.putIfAbsent(taskInfo.conversationId(), taskInfo) == null;
    }

    public Optional<TaskInfo> get(String conversationId) {
        return Optional.ofNullable(taskMap.get(conversationId));
    }

    public void remove(String conversationId) {

        taskMap.remove(conversationId);
    }

    public void remove(String conversationId, TaskInfo expectedTaskInfo) {
        if (conversationId == null || expectedTaskInfo == null) {
            return;
        }
        taskMap.remove(conversationId, expectedTaskInfo);
    }
}
