package com.neusoft.zuul8087;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableZuulProxy
@EnableRedisHttpSession
public class Zuul8087Application {

    public static void main(String[] args) {
        SpringApplication.run(Zuul8087Application.class, args);
    }

}
