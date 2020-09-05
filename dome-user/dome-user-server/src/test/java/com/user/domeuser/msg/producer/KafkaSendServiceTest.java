package com.user.domeuser.msg.producer;

import com.alibaba.fastjson.JSON;
import com.user.domeuser.DomeUserApplication;
import com.user.domeuser.msg.modle.MessageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DomeUserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ContextConfiguration
public class KafkaSendServiceTest {
    private Logger logger = LoggerFactory.getLogger(KafkaSendServiceTest.class);
    @Autowired
    private KafkaProducerService kafkaSendService;

    @Test
    public void send() {
        MessageInfo messageInfo = new MessageInfo();
        for (int i = 0; i < 1; i++) {
            //调用消息发送类中的消息发送方法
            messageInfo.setId(Long.valueOf(i));
            messageInfo.setMsg("test"+i);
            messageInfo.setSendTime(new Date());
            logger.info("kafka日志收集test:"+JSON.toJSONString(messageInfo));
            kafkaSendService.sendWarning(messageInfo);
        }
    }

}