package com.baidu.fsg.uid.buffer;

import java.util.List;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: /
 * @author: 阿星不是程序员
 **/

@FunctionalInterface
public interface BufferedUidProvider {

    List<Long> provide(long momentInSecond);
}
