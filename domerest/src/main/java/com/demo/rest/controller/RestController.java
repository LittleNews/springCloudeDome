package com.demo.rest.controller;


import com.user.domeuser.api.service.UserInfoApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author littlenew
 * @date 2020/5/13 9:32 AM
 **/
@Controller
@RequestMapping(value = "/rest")
public class RestController {
    private static Logger log = LoggerFactory.getLogger(RestController.class);

    private Integer b = 0;
    @Autowired
    private UserInfoApiService userInfoService;

    @RequestMapping(value = "/get")
    @ResponseBody
    public String getRest() {
        String result = "restget";
        return result;
    }

    @RequestMapping(value = "/user")
    @ResponseBody
    public String getUserRest(@RequestParam("id") Long id) {

        return userInfoService.getUserRest(id);
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String getRestget(@RequestParam("i") Integer i) {
        boolean s = true;
        this.b = i;
        if (this.b == 1) {
            s = false;
        }
        int count = 1;
        while (s) {
           log.info("aaaaa撒打算打算" + i);
            count++;
            log.info(count+"");
            if (count == 1000000) {
                s = false;
            }
        }

        return "aaa";
    }

    @RequestMapping(value = "/pool")
    @ResponseBody
    public String getpool(@RequestParam("i") Integer i) {

//        ExecutorService pool = new ThreadPoolExecutor();

        return "aaa";
    }



}
