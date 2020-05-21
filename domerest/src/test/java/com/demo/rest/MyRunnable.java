package com.demo.rest;

import java.util.concurrent.Callable;

/**
 * @author littlenew
 * @date 2020/5/21 11:51 AM
 **/
public class MyRunnable implements Callable<String> {
    private Integer i=null;

    public MyRunnable(Integer i) {
        this.i = i;
    }

//    public String call() throws Exception {
//        //Exception开始执行耗时操作
//        Thread.sleep(1000);
//        System.out.println("请求url");
////                restTemplate.getForObject("http://192.168.10.23:991/actuator/info", String.class);
//        return (String) "线程执行完成.";
//    }

    public String call()throws Exception{
        if(i==2) {
            Thread.sleep(3000);
            System.out.println(i + "执行中。。。。。");
        }
        return i.toString();
    }
}
