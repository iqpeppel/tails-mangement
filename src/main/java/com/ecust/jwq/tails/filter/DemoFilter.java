package com.ecust.jwq.tails.filter;

import com.github.pagehelper.BoundSqlInterceptor;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
//@WebFilter(urlPatterns = "/*")
public class DemoFilter  implements Filter {

    @Override//初始化调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("filter初始化完成了");
    }

    @Override//拦截调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);//放行
        System.out.println("拦截了");
    }

    @Override//销毁调用一次
    public void destroy() {
        Filter.super.destroy();
        System.out.println("销毁了");
    }
}
