package com.example.service_work_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceWorkServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceWorkServiceApplication.class, args);
	}

}
