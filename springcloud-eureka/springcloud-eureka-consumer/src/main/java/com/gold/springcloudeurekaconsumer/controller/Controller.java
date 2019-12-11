package com.gold.springcloudeurekaconsumer.controller;

import com.gold.springcloudeurekaconsumer.externalapi.EurekaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Controller {

    private final EurekaProducerService producerService;

    @GetMapping("/index")
    public String requestProducer() {
        return producerService.service();
    }
}
