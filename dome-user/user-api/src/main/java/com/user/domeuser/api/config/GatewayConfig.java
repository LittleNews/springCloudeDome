package com.user.domeuser.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author littlenew
 * @date 2020/5/15 11:42 AM
 **/
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "eureka.client.demo-service")
public class GatewayConfig {
    private String gateway;

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String gateway() {

        if (gateway == null) {
            System.out.println("==============================网关地址(eureka.client.demo-service.gateway)为空，无法获取到指定应用地址==============================");
        }
        return gateway;
    }
}
