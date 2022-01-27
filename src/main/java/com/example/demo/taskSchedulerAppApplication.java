package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.demo")
public class taskSchedulerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(taskSchedulerAppApplication.class, args);
	}

}
