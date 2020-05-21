package com.user.domeuser.api.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

/**
 * @author littlenew
 * @date 2020/5/21 2:20 PM
 **/
public class GatewayCallable implements Callable<Long> {

    private String url = null;

    public GatewayCallable(String url) {
        this.url = url;
    }

    @Override
    public Long call() throws Exception {
        long statTime = System.currentTimeMillis();
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        restTemplate.getForObject(url, String.class);
        long endTime = System.currentTimeMillis();

        return endTime - statTime;
    }
}
