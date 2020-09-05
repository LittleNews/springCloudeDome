package com.user.domeuser.aspect;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author littlenew
 * @date 2020/5/14 10:00 AM
 * 无法切入第三方jar 如果需要切入需要反编译第三方jar后做接口重载
 **/
//@Aspect
//@Component
public class MysqlAspect {
    private static Logger log = LoggerFactory.getLogger(MysqlAspect.class);
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

//    @Pointcut("execution(* com.user.domeuser.service.Impl.UserInfoServiceImpl.selectUserInfoById(..))")
//    @Pointcut("execution(* com.user.domeuser.mybatis.mapper.UserInfoMapper.selectByPrimaryKey(..))")
//    @Pointcut("execution(* org.springframework.jdbc.core.JdbcTemplate.*(..))")
    @Pointcut("execution(* org.mybatis.spring.SqlSessionUtils.closeSqlSession(..))")
    public void log() {

    }

    @Around("log()")
    public Object haha(ProceedingJoinPoint pjp) throws Throwable {
        log.info("++++++++++开始+++++++++++");

        log.info("class.method: " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        log.info("args: " + pjp.getArgs());

        Object[] args = pjp.getArgs();
        for(Object obj : args){

            log.info("arguments: "+obj);
        }
//        for(int i = 0;i<args.length;i++){
//            if(i==0){
//                System.out.println(args[i]);
//                args[i]=2L;
//            }else {
//                args[i] = 2L;
//            }
//        }
//        args[0] = 2L;
        Object proceed = pjp.proceed();
//        3.获取SQL
        String sql = SqlUtils.getMybatisSql(pjp, sqlSessionFactory);
        log.info("SQL:" + sql);
        log.info("++++++++++结束+++++++++++");
        return proceed;
    }


    @Around("execution(* com.alibaba.fastjson.JSON.toJSONString(java.lang.Object)) && args(obj)")
    public String parse2String(ProceedingJoinPoint join, Object obj){
        log.info("parse to String before");
        String str = "";
        try {
            str = (String) join.proceed(new Object[]{obj});
            log.info("result:"+str);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        log.info("parse to String after");
        return str;
    }

    @Before("log()")
    public void tt(JoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        log.info("The method " + methodName + " begins with " + args);

//        return joinPoint;
    }

}

