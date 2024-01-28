package com.feignClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemoFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoFeignApplication.class, args);
    }

}
