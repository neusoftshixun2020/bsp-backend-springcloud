package com.neusoft.wallet8086;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan({"com.neusoft.wallet8086.*.mapper"})
@EnableDiscoveryClient
@SpringBootApplication
public class Wallet8086Application {

    public static void main(String[] args) {
        SpringApplication.run(Wallet8086Application.class, args);
    }

}
