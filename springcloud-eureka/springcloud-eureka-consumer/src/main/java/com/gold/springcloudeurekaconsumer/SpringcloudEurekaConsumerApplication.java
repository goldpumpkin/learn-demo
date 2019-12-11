package com.gold.springcloudeurekaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringcloudEurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaConsumerApplication.class, args);
    }

}
