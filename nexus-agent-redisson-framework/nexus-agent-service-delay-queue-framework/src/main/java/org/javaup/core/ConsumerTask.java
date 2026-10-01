package org.javaup.core;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 延迟队列 消费者接口
 * @author: 阿星不是程序员
 **/
public interface ConsumerTask {

    void execute(String content);

    String topic();
}
