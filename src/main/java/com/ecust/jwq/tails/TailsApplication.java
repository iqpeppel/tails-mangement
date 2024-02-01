package com.ecust.jwq.tails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//启动类
@EnableAspectJAutoProxy
@ServletComponentScan//支持filter
@SpringBootApplication
public class TailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TailsApplication.class, args);
    }

}
