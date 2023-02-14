package com.gold.spring_requested_scope_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringRequestedScopeDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRequestedScopeDataApplication.class, args);
    }

}
