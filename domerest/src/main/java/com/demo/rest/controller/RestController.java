package com.demo.rest.controller;


import com.user.domeuser.api.service.UserInfoApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author littlenew
 * @date 2020/5/13 9:32 AM
 **/
@Controller
@RequestMapping(value = "/rest")
public class RestController {

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
    public String getUserRest(@RequestParam("id")Long id) {

        return userInfoService.getUserRest(id);
    }

}
