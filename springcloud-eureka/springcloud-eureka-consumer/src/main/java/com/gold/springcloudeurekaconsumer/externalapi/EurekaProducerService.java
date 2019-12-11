package com.gold.springcloudeurekaconsumer.externalapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "eureka-producer")
public interface EurekaProducerService {

    @GetMapping("/serv")
    public String service();


}
