package com.user.domeuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.user.domeuser"})
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DomeUserApplication {

    // ribbon需要配置，负载均衡
    @Autowired
    private RestTemplateBuilder builder;

    // ribbon需要配置，负载均衡
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(DomeUserApplication.class, args);
        System.out.println("ok");
    }

}
