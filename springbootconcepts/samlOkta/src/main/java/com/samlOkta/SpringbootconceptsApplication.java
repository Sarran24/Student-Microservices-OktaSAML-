package com.samlOkta;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import com.samlOkta.config.SecurityConfiguration;
import controller.HomeController;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackageClasses = SecurityConfiguration.class)
@ComponentScan(basePackageClasses = HomeController.class)
public class SpringbootconceptsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootconceptsApplication.class, args);
	}

}
