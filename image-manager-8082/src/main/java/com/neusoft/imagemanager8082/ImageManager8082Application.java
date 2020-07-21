package com.neusoft.imagemanager8082;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan({"com.neusoft,imagemanager8082.*.mapper"})
@EnableDiscoveryClient
@SpringBootApplication
public class ImageManager8082Application {

    public static void main(String[] args) {
        SpringApplication.run(ImageManager8082Application.class, args);
    }

}
