package com.user.domeuser.msg.producer;

import com.user.domeuser.msg.config.KafkaTopicConfig;
import com.user.domeuser.msg.modle.MessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 * @author littlenew
 * @date 2020/5/25 5:45 PM
 **/
@Service
public class KafkaProducerService {
    private Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private BinderAwareChannelResolver resolver;

    /**
     * 发送消息到指定topic
     * 若发现kafka中没有该topic，它会自动创建一个topic
     * @param warnings
     * @return
     */
    public String sendWarning(MessageInfo warnings) {
        logger.info("==========发送消息{}", warnings);
        // 获取topic，然后发送消息到kafka的topic
        MessageChannel messageChannel = resolver.resolveDestination(KafkaTopicConfig.TOPIC);
        messageChannel.send(MessageBuilder
                .withPayload(warnings)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());

        return "send msg ok";
    }
}
