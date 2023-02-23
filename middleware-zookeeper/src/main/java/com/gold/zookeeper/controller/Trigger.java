package com.gold.zookeeper.controller;


import com.gold.zookeeper.service.LeaderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Trigger {

    private final LeaderService leaderService;

    @GetMapping("hit")
    public String startInstance() throws InterruptedException {
//        new Thread(leaderService::take).start();
        leaderService.take();

        Thread.sleep(10000L);
        return "start";
    }
}
