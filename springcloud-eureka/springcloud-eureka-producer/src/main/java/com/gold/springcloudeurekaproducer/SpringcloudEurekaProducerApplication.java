package com.gold.springcloudeurekaproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudEurekaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaProducerApplication.class, args);
    }

}
