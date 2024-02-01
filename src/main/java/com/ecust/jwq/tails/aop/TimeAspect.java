package com.ecust.jwq.tails.aop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around; // 添加此行

@Slf4j
@Component
@Aspect
public class  TimeAspect {

    @Around("execution(* com.ecust.jwq.tails.service.*.*(..))") // 示例切入点表达式，根据实际情况修改
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable { // 添加 throws Throwable
        long begin = System.currentTimeMillis();
        Object result = null; // 初始化 result

        try {
            // 执行被切入的方法
            result =  joinPoint.proceed() /* 执行方法的代码 */;
            return result; // 在方法执行成功时返回结果
        } catch (Throwable e) {
            log.error("An error occurred: {}", e.getMessage());
            throw e; // 在捕获异常时将异常继续抛出
        } finally {
            long end = System.currentTimeMillis();
            log.info("Method execution time: {} milliseconds", (end - begin));
        }
    }

}

