package org.javaup.ai.manage.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.javaup.ai.manage.config.DocumentManageProperties.Kafka;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 配置类
 * @author: 阿星不是程序员
 **/

@EnableKafka
@Configuration
@EnableConfigurationProperties(DocumentManageProperties.class)
public class DocumentManageKafkaConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "app.manage.kafka", name = "auto-create-topics", havingValue = "true", matchIfMissing = true)
    public NewTopic documentParseTopic(DocumentManageProperties properties) {
        Kafka kafka = properties.getKafka();
        return TopicBuilder.name(kafka.getParseTopic()).partitions(1).replicas(1).build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "app.manage.kafka", name = "auto-create-topics", havingValue = "true", matchIfMissing = true)
    public NewTopic documentIndexTopic(DocumentManageProperties properties) {
        Kafka kafka = properties.getKafka();
        return TopicBuilder.name(kafka.getIndexTopic()).partitions(1).replicas(1).build();
    }
}
