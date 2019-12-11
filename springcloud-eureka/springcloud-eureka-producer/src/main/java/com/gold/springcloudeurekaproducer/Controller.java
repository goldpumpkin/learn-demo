package com.gold.springcloudeurekaproducer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/serv")
    public String service() {
        return "producer";
    }
}
