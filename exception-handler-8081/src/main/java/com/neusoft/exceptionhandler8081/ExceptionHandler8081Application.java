package com.neusoft.exceptionhandler8081;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ExceptionHandler8081Application {

    public static void main(String[] args) {
        SpringApplication.run(ExceptionHandler8081Application.class, args);
    }

}
