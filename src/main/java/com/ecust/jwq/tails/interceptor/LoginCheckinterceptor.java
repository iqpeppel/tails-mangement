package com.ecust.jwq.tails.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.ecust.jwq.tails.pojo.Result;
import com.ecust.jwq.tails.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
@Slf4j
public class LoginCheckinterceptor implements HandlerInterceptor {
    @Override//资源运行前运行返回值为true为放行为false则为不放行
    public boolean preHandle(HttpServletRequest rep, HttpServletResponse reps, Object handler) throws Exception {
        System.out.println("prehandle 运行了：...");
        //获取url
        String url  = rep.getRequestURI().toString();
        log.info("请求的URL:{}",url);
        //判断url是否包含login如果有则说明是登录操作放行
        if(url.contains("login")){
            log.info("登录操作");
            return true;
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
            return  false;
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
            return false;
        }
        //放行
        log.info("令牌合法，放行");
      return  true;
    }



    @Override///资源运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            System.out.println("posthandle 运行了");

    }

    @Override//渲染完毕后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterCompletion");
    }
}
