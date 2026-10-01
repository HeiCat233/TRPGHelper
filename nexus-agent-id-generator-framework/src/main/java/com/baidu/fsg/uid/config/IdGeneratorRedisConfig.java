package com.baidu.fsg.uid.config;

import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 对百度开源id生成器进行redis适配
 * @author: 阿星不是程序员
 **/
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty("spring.data.redis.host")
public class IdGeneratorRedisConfig {

    @Bean("idGeneratorRedisTemplate")
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean("disposableWorkerIdAssigner")
    public WorkerIdAssigner redisDisposableWorkerIdAssigner(@Qualifier("idGeneratorRedisTemplate") RedisTemplate redisTemplate){
        RedisDisposableWorkerIdAssigner redisDisposableWorkerIdAssigner = new RedisDisposableWorkerIdAssigner(redisTemplate);
        return redisDisposableWorkerIdAssigner;
    }
}
