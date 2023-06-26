package com.gold.springcloudzuuleureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// TODO ZULL example
@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudZuulEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudZuulEurekaApplication.class, args);
    }

}
