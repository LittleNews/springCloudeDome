package com.demozuul.fitter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;

import java.util.List;


/**
 * 微服务网关
 * 修改网关过滤规则
 */
@Component
public class AccessFilter extends ZuulFilter {

    //免认证TOKEN请求白名单
    private static List<String> requestURLWhiteList = new ArrayList<String>(){{
        add("/zuul/login");
        add("/s-uc/account/getUserType");
        add("/s-uc/account/toLogout");
        add("/s-uc/account/checkOldPasswd");
        add("/s-uc/account/resetPsw");
        add("/s-uc/account/beforeVerity");
        add("extraFiles");
        add("materials");
        add("findAccount");
        add("sendVerifyCode");
        add("verifyCode");
        add("exportExcel");
        add("down");
        add("Excel");
        add("static");
    }};

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        return null;
    }
}
