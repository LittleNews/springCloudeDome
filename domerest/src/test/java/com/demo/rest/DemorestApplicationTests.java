package com.demo.rest;

import com.alibaba.fastjson.JSON;
import com.demo.rest.model.job.HelloJob;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

@SpringBootTest
class DemorestApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        final ExecutorService exec = Executors.newFixedThreadPool(4);

//        Callable<String> call = new Callable<String>() {
//            public String call() throws Exception {
//                //开始执行耗时操作
//                Thread.sleep(1000);
//                System.out.println("请求url");
////                restTemplate.getForObject("http://192.168.10.23:991/actuator/info", String.class);
//                return "线程执行完成.";
//            }
//        };
        Long stattime = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            Long stat = System.currentTimeMillis();
            try {
                Future<String> future = exec.submit(new MyRunnable(i));
                String obj = future.get(1000 * 1, TimeUnit.MILLISECONDS); //任务处理超时时间设为 1 秒
                System.out.println("任务成功返回:"+i +":"+ obj);
            } catch (TimeoutException ex) {
                System.out.println("处理超时啦....");
//                List<Runnable> st= exec.shutdownNow();
//                System.out.println(JSON.toJSONString(st));
                ex.printStackTrace();

            } catch (Exception e) {
                System.out.println("处理失败.");
                e.printStackTrace();
                exec.shutdownNow();
            }
            Long end = System.currentTimeMillis();
            System.out.println("线程执行时间："+(end-stat));
        }
        Long endtime = System.currentTimeMillis();
        System.out.println("线程池执行时间："+(endtime-stattime));
        // 停止线程
        exec.shutdownNow();
        // 关闭线程池
        exec.shutdown();
    }

    public static int getRandomExcept(int RandMax, Set ExceptNums) {
        Random rand = new Random();

        while (true) {
            int num = rand.nextInt(RandMax);
            if (ExceptNums.contains(num)) {
                continue;
            } else {
                return num;
            }
        }
    }

    public static int getRandomNorepit(int RandMax, Set RepitNums) {
        int num = getRandomExcept(RandMax, RepitNums);
        RepitNums.add(num);
        return num;
    }

//1
//		try {
//			// Grab the Scheduler instance from the Factory
//			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//
//			// and start it off
//			scheduler.start();
//
//			// define the job and tie it to our HelloJob class
//			JobDetail job = newJob(HelloJob.class)
//					.withIdentity("job1", "group1")
//					.build();
//
//			// Trigger the job to run now, and then repeat every 40 seconds
//			Trigger trigger = newTrigger()
//					.withIdentity("trigger1", "group1")
//					.startNow()
//					.withSchedule(simpleSchedule()
//							.withIntervalInSeconds(40)
//							.repeatForever())
//					.build();
//			// Tell quartz to schedule the job using our trigger
//			scheduler.scheduleJob(job, trigger);
//			try {
//				Thread.sleep(6000);
//			}catch (InterruptedException e){
//
//			}
//			scheduler.shutdown();
//
//		} catch (SchedulerException se) {
//			se.printStackTrace();
//		}
    //2
//		try {
//			SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
//
//			Scheduler sched = schedFact.getScheduler();
//
//			sched.start();
//
//			// define the job and tie it to our HelloJob class
//			JobDetail job = newJob(HelloJob.class)
//					.withIdentity("myJob", "group1")
//					.usingJobData("h1","hh1")
//					.build();
//
//			// Trigger the job to run now, and then every 40 seconds
////			Trigger trigger = newTrigger()
////					.withIdentity("myTrigger", "group1")
////					.startNow()
////					.withSchedule(simpleSchedule()
////							.withIntervalInSeconds(10)
////							.repeatForever())
////					.build();
//
//			// Tell quartz to schedule the job using our trigger
//            Trigger trigger = (SimpleTrigger) newTrigger()
//                    .withIdentity("trigger5", "group1")
//                    .startAt(futureDate(1, DateBuilder.IntervalUnit.MINUTE)) // use DateBuilder to create a date in the future
//                    .forJob("myJobKey") // identify job with its JobKey
//                    .build();
//			sched.scheduleJob(job, trigger);
//
//		}catch (SchedulerException se){
//			se.printStackTrace();
//		}

//	}
}
