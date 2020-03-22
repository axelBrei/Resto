package com.axelynicky.api_gateway.Configuration;

import com.axelynicky.api_gateway.Utils.JwtTokenUtility;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Enumeration;

public class ZuulFilter extends com.netflix.zuul.ZuulFilter {
    private static final String ACCESS_TOKEN_HEADER = "ACCESS_TOKEN";
    private static final String AUTHORIZATION_HEADER = "authorization";
    private static final String USER_TOKEN_HEADER = "USER_TOKEN";
    private static final String HEADER_PREFIX = "Bearer ";
    private static final int FILTER_ORDER = 6;

    Logger log = LoggerFactory.getLogger(ZuulFilter.class);

    JwtTokenUtility jwtTokenUtility;

    @Autowired
    private void init() {
        jwtTokenUtility = new JwtTokenUtility();
    }


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set(ACCESS_TOKEN_HEADER, HEADER_PREFIX + jwtTokenUtility.createAutorizationToken());
        ctx.addZuulRequestHeader(ACCESS_TOKEN_HEADER, HEADER_PREFIX + jwtTokenUtility.createAutorizationToken());

        if (ctx.getRequest().getHeader(AUTHORIZATION_HEADER) != null){
            ctx.set(USER_TOKEN_HEADER, ctx.getRequest().getHeader(AUTHORIZATION_HEADER));
            ctx.addZuulRequestHeader(USER_TOKEN_HEADER, ctx.getRequest().getHeader(AUTHORIZATION_HEADER));
        }
        return null;
    }
}
