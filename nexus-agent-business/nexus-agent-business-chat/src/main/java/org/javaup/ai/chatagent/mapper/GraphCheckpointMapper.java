package org.javaup.ai.chatagent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.javaup.ai.chatagent.data.GraphCheckpoint;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: Mapper层
 * @author: 阿星不是程序员
 **/

@Mapper
public interface GraphCheckpointMapper extends BaseMapper<GraphCheckpoint> {
}
