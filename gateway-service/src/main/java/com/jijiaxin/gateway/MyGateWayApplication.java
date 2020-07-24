package com.jijiaxin.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MyGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyGateWayApplication.class,args);
    }
}
