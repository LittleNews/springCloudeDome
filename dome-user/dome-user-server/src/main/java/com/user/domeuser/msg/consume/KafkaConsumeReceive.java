package com.user.domeuser.msg.consume;

import com.alibaba.fastjson.JSON;
import com.user.domeuser.msg.config.KafkaTopicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 同样到topic不同的groupId都能接收同样都信息
 * 集群中同一个groupId只会被一个客户端消费一次
 * @author littlenew
 * @date 2020/5/26 11:49 AM
 **/
@Component
public class KafkaConsumeReceive {
    private Logger logger = LoggerFactory.getLogger(KafkaConsumeReceive.class);

    /**
     * 测试消息接收，接收对象用Object
     * 返回消息对象
     */
    @KafkaListener(topics = KafkaTopicConfig.TOPIC, groupId = "test_01")
    public void receive(Object record) {
        logger.info("============消息接收test_01：" + JSON.toJSONString(record));
    }

    /**
     * 测试消息接收，接收对象用String
     * 返回消息实体
     */
    @KafkaListener(topics = KafkaTopicConfig.TOPIC, groupId = "test_02")
    public void receive(String record) {
        logger.info("============消息接收test_02：" + record);
    }
}
