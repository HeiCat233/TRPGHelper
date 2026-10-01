package org.javaup.ai.chatagent.rag.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 配置类
 * @author: 阿星不是程序员
 **/

@Configuration
public class ChatRagExecutorConfiguration {

    @Bean(name = "chatRagExecutorService", destroyMethod = "shutdown")
    public ExecutorService chatRagExecutorService() {

        return newFixedThreadPool("chat-rag-executor-", 8, 256);
    }

    @Bean(name = "chatMemorySummaryExecutorService", destroyMethod = "shutdown")
    public ExecutorService chatMemorySummaryExecutorService() {

        return newFixedThreadPool("chat-memory-summary-", 2, 32);
    }

    @Bean(name = "chatPostProcessExecutorService", destroyMethod = "shutdown")
    public ExecutorService chatPostProcessExecutorService() {
        return newFixedThreadPool("chat-post-process-", 2, 64);
    }

    private ExecutorService newFixedThreadPool(String threadNamePrefix, int poolSize, int queueCapacity) {
        AtomicInteger threadCounter = new AtomicInteger(1);

        return new ThreadPoolExecutor(
            poolSize,
            poolSize,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(queueCapacity),
            runnable -> {
                Thread thread = new Thread(runnable);
                thread.setName(threadNamePrefix + threadCounter.getAndIncrement());
                thread.setDaemon(true);
                return thread;
            },
            new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}
