package com.user.domeuser.api.service.impl;

import com.user.domeuser.api.config.GatewayCallable;
import com.user.domeuser.api.config.GatewayConfig;
import com.user.domeuser.api.config.RestTemplateBuilderInit;
import com.user.domeuser.api.service.UserInfoApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author littlenew
 * @date 2020/5/12 6:22 PM
 **/
@Service
public class UserInfoApiServiceImpl implements UserInfoApiService {
    private static Logger log = LoggerFactory.getLogger(GatewayCallable.class);
    @Autowired
    private RestTemplateBuilderInit restTemplateBuilderInit;
    @Autowired
    private GatewayConfig gatewayConfig;


    @Override
    public String getUserRest(Long id) {
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        String result = restTemplateBuilderInit.restTemplate().getForObject(gatewayConfig.getGatewayUrl() + "get1?id={id}", String.class, map);
        log.info(result);
        return result;
    }
}
