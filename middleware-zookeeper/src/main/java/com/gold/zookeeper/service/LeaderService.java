package com.gold.zookeeper.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class LeaderService {

    private final CuratorFramework curatorFramework;

    /**
     * 目录必须带 "/"
     */
    private static final String PATH = "/crown";

    public void take() {
        final LeaderSelector selector = build();
        // 异步执行
        selector.start();
    }

    public void hold() {
        final LeaderSelector selector = build();
        selector.autoRequeue();
        // 异步执行
        selector.start();
    }


    public LeaderSelector build() {
        return new LeaderSelector(curatorFramework, PATH, new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                // 抢到锁
                log.info("[LeaderSelector] take the crown");
                Thread.sleep(20000L);
                log.info("[LeaderSelector] sleep over...");
            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                log.info("[LeaderSelector] state changed, status is {}", connectionState.name());
            }
        });
    }
}
