package com.baidu.fsg.uid.config;

import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import org.javaup.enums.BaseCode;
import org.javaup.exception.SuperAgentFrameException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: redis配置生成work_id
 * @author: 阿星不是程序员
 **/
public class RedisDisposableWorkerIdAssigner implements WorkerIdAssigner {

    private RedisTemplate redisTemplate;

    public RedisDisposableWorkerIdAssigner (RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public long assignWorkerId() {
        String key = "uid_work_id";
        Long increment = redisTemplate.opsForValue().increment(key);
        return Optional.ofNullable(increment).orElseThrow(() -> new SuperAgentFrameException(BaseCode.UID_WORK_ID_ERROR));
    }
}
