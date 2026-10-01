package org.javaup.servicelock.info;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 分布式锁 处理失败抽象
 * @author: 阿星不是程序员
 **/
public interface LockTimeOutHandler {

    void handler(String lockName);
}
