package com.ecust.jwq.tails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class TailsApplicationTests {

    @Test
    void contextLoads() {
    }
    //生成jwt令牌
    @Test
    public  void testGenJwt(){
        HashMap<String, Object> Claims = new HashMap<>();
        Claims.put("id",1);
        Claims.put("name","tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itjwq")//签名算法
                .setClaims(Claims)//自定义内容
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置有效期
                .compact();
        System.out.println(jwt);
    }
    //解析jwt令牌
    @Test
    public  void  testParseJwt(){
        Claims claims  = Jwts.parser()
                .setSigningKey("itjwq")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcwNDc4ODcyMX0.5NBpY8j0bCY2AY96nvj5Jbfpt1ZtB2dAoSf9BGjBj_8")
                .getBody();
        System.out.println(claims);
    }


}
