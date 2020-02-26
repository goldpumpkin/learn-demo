package com.gold.springcloudzuuleureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

// TODO ZULL example
@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudZuulEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudZuulEurekaApplication.class, args);
    }

}
