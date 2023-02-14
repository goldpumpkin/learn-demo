package com.gold.springremove_if_else;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringRemoveIfElseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRemoveIfElseApplication.class, args);
	}

}
