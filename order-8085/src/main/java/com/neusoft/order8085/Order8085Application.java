package com.neusoft.order8085;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan({"com.neusoft.order8085.*.mapper"})
@EnableDiscoveryClient
@SpringBootApplication
public class Order8085Application {

    public static void main(String[] args) {
        SpringApplication.run(Order8085Application.class, args);
    }

}
