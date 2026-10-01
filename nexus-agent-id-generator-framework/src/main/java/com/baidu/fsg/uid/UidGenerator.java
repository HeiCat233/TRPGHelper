package com.baidu.fsg.uid;

import com.baidu.fsg.uid.exception.UidGenerateException;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 生成器
 * @author: 阿星不是程序员
 **/

public interface UidGenerator {

    long getUid() throws UidGenerateException;

    long getId();

    long getOrderNumber(long userId,long tableCount,long databaseCount);

    long getOrderNumber(long userId);

    @Deprecated
    long getOrderNumber(long userId,long tableCount);

    String parseUid(long uid);

}
