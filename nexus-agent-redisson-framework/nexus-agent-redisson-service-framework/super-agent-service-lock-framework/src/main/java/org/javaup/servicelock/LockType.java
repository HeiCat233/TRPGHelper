package org.javaup.servicelock;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 分布式锁 锁类型
 * @author: 阿星不是程序员
 **/
public enum LockType {

    Reentrant,

    Fair,

    Read,

    Write;

    LockType() {
    }

}
