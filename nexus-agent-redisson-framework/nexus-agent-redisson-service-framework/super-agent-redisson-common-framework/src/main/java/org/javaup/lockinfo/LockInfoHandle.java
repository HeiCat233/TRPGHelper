package org.javaup.lockinfo;

import org.aspectj.lang.JoinPoint;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 锁信息抽象
 * @author: 阿星不是程序员
 **/
public interface LockInfoHandle {

    String getLockName(JoinPoint joinPoint, String name, String[] keys);

    String simpleGetLockName(String name,String[] keys);
}
