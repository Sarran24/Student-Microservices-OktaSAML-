package com.aspectj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AspectDemoApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(AspectDemoApplication.class, args);
	}

}
