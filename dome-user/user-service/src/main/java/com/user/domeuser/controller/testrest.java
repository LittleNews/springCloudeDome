package com.user.domeuser.controller;

import com.alibaba.fastjson.JSON;
import com.user.domeuser.api.service.UserInfoApiService;
import com.user.domeuser.mybatis.entity.UserInfo;
import com.user.domeuser.service.BaseService;
import com.user.domeuser.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author littlenew
 * @date 2020/5/12 5:12 PM
 **/
@Controller
public class testrest {
    @Autowired
    private UserInfoApiService userInfoApiService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/get")
    @ResponseBody
    public Object getUserRest() {
        Long id = baseService.diteratorId("user_info");
        return String.format("id:%s;所在表:%s", id, id % 4);
    }

    @RequestMapping(value = "/get2")
    @ResponseBody
    public String get2() {
        return userInfoApiService.getUserRest();
    }

    @RequestMapping(value = "/get1")
    @ResponseBody
    public String get1(@RequestParam("id") Long id) {
        return JSON.toJSONString(userInfoService.selectUserInfoById(id));
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo);
        return JSON.toJSONString(userInfoService.saveUserInfo(userInfo));
    }
    @RequestMapping(value = "/up")
    @ResponseBody
    public String up(@RequestBody UserInfo userInfo) {
        return JSON.toJSONString(userInfoService.upDateById(userInfo));
    }
}
