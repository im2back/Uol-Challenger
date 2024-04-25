package com.im2back.github.playermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PlayerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerMicroserviceApplication.class, args);
	}

}
