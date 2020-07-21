package com.neusoft.userpermission8083;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan({"com.neusoft.userpermission8083.*.mapper"})
@EnableDiscoveryClient
@SpringBootApplication
public class UserPermission8083Application {

    public static void main(String[] args) {
        SpringApplication.run(UserPermission8083Application.class, args);
    }

}
