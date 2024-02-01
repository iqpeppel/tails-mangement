package com.ecust.jwq.tails.filter;

import com.alibaba.fastjson.JSONObject;
import com.ecust.jwq.tails.pojo.Result;
import com.ecust.jwq.tails.utils.JwtUtils;
import com.github.pagehelper.util.StringUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.print.DocFlavor;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest rep = (HttpServletRequest) servletRequest;
        HttpServletResponse  reps = (HttpServletResponse) servletResponse;
        //获取url
       String url  = rep.getRequestURI().toString();
       log.info("请求的URL:{}",url);
       //判断url是否包含login如果有则说明是登录操作放行
        if(url.contains("login")){
            filterChain.doFilter(servletRequest,servletResponse);
            log.info("登录操作");
            return;
        }
        //获取jwt令牌
        String jwt = rep.getHeader("token");
        //判断jwt是否为空
        if(!StringUtils.hasLength(jwt)){
            log.info("请求的token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换为JSON----->Fastjson
            String notLogin  = JSONObject.toJSONString(error);
            reps.getWriter().write(notLogin);
            return;
        }
        //解析token如果失败返回错误结果未登录
       try {
           JwtUtils.parseJWT(jwt);
       }catch ( Exception e){
           e.printStackTrace();
           log.info("解析令牌失败，返回未登录的信息");
           Result error = Result.error("NOT_LOGIN");
           //手动转换为JSON----->Fastjson
           String notLogin  = JSONObject.toJSONString(error);
           reps.getWriter().write(notLogin);
           return;
       }
       //放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
