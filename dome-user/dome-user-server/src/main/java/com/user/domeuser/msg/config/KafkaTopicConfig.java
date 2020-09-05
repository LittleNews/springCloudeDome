package com.user.domeuser.msg.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author littlenew
 * @date 2020/5/25 5:31 PM
 **/
@Component
public class KafkaTopicConfig {
    private static final Logger logger = LoggerFactory.getLogger(KafkaTopicConfig.class);
    @Autowired
    private BinderAwareChannelResolver resolver;
    public static final String TOPIC="test_01";

    @PostConstruct
    public void initKafkaTopic() {
        try {
            // 这行代码是动态去生成topic的，先检查kafka中有没有传入的topic，有就直接返回topic，没有则新建
            MessageChannel messageChannel = resolver.resolveDestination(TOPIC);
        } catch (Exception e) {
            logger.error("kafka.topic初始化创建失败..", e);
        }
    }
}
