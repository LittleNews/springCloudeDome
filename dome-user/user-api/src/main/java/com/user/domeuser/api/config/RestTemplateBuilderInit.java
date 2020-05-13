package com.user.domeuser.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author littlenew
 * @date 2020/5/12 4:59 PM
 **/
@Service
public class RestTemplateBuilderInit  {

    @Autowired
    private RestTemplateBuilder builder;


    public RestTemplate restTemplate() {
        return builder.build();
    }


}
