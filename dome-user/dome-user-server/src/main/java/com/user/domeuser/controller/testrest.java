package com.user.domeuser.controller;

import com.alibaba.fastjson.JSON;
import com.user.domeuser.api.service.UserInfoApiService;
import com.user.domeuser.msg.modle.MessageInfo;
import com.user.domeuser.msg.producer.KafkaProducerService;
import com.user.domeuser.mybatis.entity.UserInfo;
import com.user.domeuser.service.BaseService;
import com.user.domeuser.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * @author littlenew
 * @date 2020/5/12 5:12 PM
 **/
@Controller
public class testrest {
    private static Logger log = LoggerFactory.getLogger(testrest.class);
    @Autowired
    private UserInfoApiService userInfoApiService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private KafkaProducerService kafkaMsgService;

    @RequestMapping(value = "/get")
    @ResponseBody
    public Object getUserRest() {
        Long id = baseService.diteratorId("user_info");
        return String.format("id:%s;所在表:%s", id, id % 4);
    }

    @RequestMapping(value = "/getapi")
    @ResponseBody
    public String get2(@RequestParam("id") Long id) {
        return userInfoApiService.getUserRest(id);
    }

    @RequestMapping(value = "/get1")
    @ResponseBody
    public String get1(@RequestParam("id") Long id) {
        return JSON.toJSONString(userInfoService.selectUserInfoById(id));
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(@RequestBody UserInfo userInfo) {
        log.info(JSON.toJSONString(userInfo));
        return JSON.toJSONString(userInfoService.saveUserInfo(userInfo));
    }

    @RequestMapping(value = "/up")
    @ResponseBody
    public String up(@RequestBody UserInfo userInfo) {
        return JSON.toJSONString(userInfoService.upDateById(userInfo));
    }

    @RequestMapping(value = "/ka")
    @ResponseBody
    public String kafka() {
        log.info("kafka log test");
        MessageInfo warnings = new MessageInfo();
        warnings.setId(1L);
        warnings.setMsg("testMsg");
        warnings.setSendTime(new Date());

        String s = null;
        s = kafkaMsgService.sendWarning(warnings);
        return "ok" ;
    }
}
