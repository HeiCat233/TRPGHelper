package org.javaup.util;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 分布式锁 方法类型执行 有返回值的业务
 * @author: 阿星不是程序员
 **/
@FunctionalInterface
public interface TaskCall<V> {

    V call();
}
