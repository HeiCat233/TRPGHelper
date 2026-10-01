package org.javaup.toolkit;

import lombok.Data;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: workId和dataCenterId对象
 * @author: 阿星不是程序员
 **/
@Data
public class WorkDataCenterId {

    private Long workId;

    private Long dataCenterId;
}
