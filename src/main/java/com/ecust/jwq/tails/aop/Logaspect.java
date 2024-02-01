package com.ecust.jwq.tails.aop;

import com.alibaba.fastjson.JSONObject;
import com.ecust.jwq.tails.mapper.OperateLogMapper;
import com.ecust.jwq.tails.pojo.OperateLog;
import com.ecust.jwq.tails.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class Logaspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;
    @Around("@annotation(com.ecust.jwq.tails.anno.Log)")
    public  Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //操作人id-当前登录id
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer  UserId = (Integer) claims.get("id");
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String operateName = joinPoint.getTarget().getClass().getName();
        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        long begin = System.currentTimeMillis();
        //方法返回值
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        String resultJson = JSONObject.toJSONString(result);

        //操作耗时
        Long costTime  = begin - end ;

        OperateLog operateLog =  new OperateLog(null, UserId , operateTime ,operateName,methodName,methodParams,resultJson,costTime);
        operateLogMapper.insert(operateLog);
        log.info("aop记录操作日志：{}",operateLog);
        return  result;
    }

}
