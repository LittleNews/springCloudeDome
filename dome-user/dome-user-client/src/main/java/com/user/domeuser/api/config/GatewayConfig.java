package com.user.domeuser.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

/**
 * zuul集群保证zuul链接可用和快速反应
 *
 * @author littlenew
 * @date 2020/5/15 11:42 AM
 **/
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "eureka.client.demo-service")
@EnableScheduling
public class GatewayConfig {
    private static Logger log = LoggerFactory.getLogger(GatewayCallable.class);
    /**
     * 与zuul建立心跳时间/秒（更新zuul可用ip时间）
     */
    private static final int HEARTBEAT = 20;
    /**
     * 与zuul建立链接的超时等待时间/秒
     * 超时时间越超过设置时间将舍弃当前url使用其它url
     * 如果超时时间过短将导致无可以url
     * 各种原因都会导致服务器间的调用超时 建议默认值为1秒
     */
    private static final int TIMEOUT = 1;

    /**
     * 配置文件url
     */
    private String gateway;
    /**
     * 可用地址
     */
    private String gatewayUrl;

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }


    /**
     * 散列配置的zuul地址，耗时最短地址
     * 方在gatewayUrl中
     * 每十秒执行一次与注册中心做心跳检查
     */
    @Scheduled(fixedDelay = 1000 * HEARTBEAT)
    public void gateway() {
        try {
            log.info("==============================网关地址(eureka.client.demo-service.gateway)为:" + gateway + "==============================");
            String[] urls = this.gateway.split(",");
            if (urls.length > 1) {
                gatweayThreadPool(urls);
            } else {
                gatewayUrl = urls[0];
            }
            if (gatewayUrl.isEmpty()) {
                log.info("==============================网关地址(eureka.client.demo-service.gateway)配置，无可用地址==============================");
            } else {
                log.info("最终使用地址:" + gatewayUrl);
            }
        } catch (Exception e) {
            log.info("==============================网关地址(eureka.client.demo-service.gateway)为空，无法获取到指定应用地址==============================");
        }

    }

    private int getRandomExcept(int randMax, Set exceptNums) {
        Random rand = new Random();

        while (true) {
            int num = rand.nextInt(randMax);
            if (exceptNums.contains(num)) {
                continue;
            } else {
                return num;
            }
        }
    }

    private int getRandomNorepit(int randMax, Set repitNums) {
        int num = getRandomExcept(randMax, repitNums);
        repitNums.add(num);
        return num;
    }

    /**
     * 线程池
     *
     * @param urls
     */
    private void gatweayThreadPool(String[] urls) {
        final ExecutorService exec = Executors.newFixedThreadPool(urls.length);
        Long stattime = System.currentTimeMillis();
        Set<Integer> set = new HashSet<>();
        long cs = 0L;
        for (int i = 0; i < urls.length; i++) {
            int urlNum = getRandomNorepit(urls.length, set);
            log.info(urlNum+"");
            String testUrl = urls[urlNum].replace("demo-service/", "actuator/info");
            Long stat = System.currentTimeMillis();
            try {
                Future<Long> future = exec.submit(new GatewayCallable(testUrl));
                //任务处理超时时间设为 1 秒
                Long obj = future.get(1000 * TIMEOUT, TimeUnit.MILLISECONDS);
                if (cs == 0L || obj < cs) {
                    cs = obj;
                    gatewayUrl = urls[urlNum];
                }
                log.info("任务成功返回，可用url:" + testUrl + ";耗时:" + obj);
            } catch (TimeoutException ex) {
                log.info("处理超时啦....");
                ex.printStackTrace();

            } catch (Exception e) {
                log.info("尝试链接：" + testUrl + "当前地址不可用");
                e.printStackTrace();
            }
            Long end = System.currentTimeMillis();
            log.info("线程执行时间：" + (end - stat));

        }
        Long endtime = System.currentTimeMillis();
        log.info("线程池执行时间：" + (endtime - stattime));
        // 停止线程
        exec.shutdownNow();
        // 关闭线程池
        exec.shutdown();
    }
}
