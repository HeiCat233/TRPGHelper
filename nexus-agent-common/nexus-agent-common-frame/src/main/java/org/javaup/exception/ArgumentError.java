package org.javaup.exception;

import lombok.Data;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 异常类
 * @author: 阿星不是程序员
 **/

@Data
public class ArgumentError {

	private String argumentName;

	private String message;
}
