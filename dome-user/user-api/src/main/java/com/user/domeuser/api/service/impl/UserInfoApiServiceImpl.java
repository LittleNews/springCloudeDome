package com.user.domeuser.api.service.impl;

import com.user.domeuser.api.config.RestTemplateBuilderInit;
import com.user.domeuser.api.service.UserInfoApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author littlenew
 * @date 2020/5/12 6:22 PM
 **/
@Service
public class UserInfoApiServiceImpl implements UserInfoApiService {
    @Autowired
    private RestTemplateBuilderInit restTemplateBuilderInit ;

    public String getUserRest() {
        String result = restTemplateBuilderInit.restTemplate().getForObject("http://127.0.0.1:9910/demo-service/get", String.class);
        return result;
    }
}
