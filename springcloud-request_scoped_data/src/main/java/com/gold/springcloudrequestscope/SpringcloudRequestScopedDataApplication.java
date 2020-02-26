package com.gold.springcloudrequestscope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringcloudRequestScopedDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudRequestScopedDataApplication.class, args);
    }

}
