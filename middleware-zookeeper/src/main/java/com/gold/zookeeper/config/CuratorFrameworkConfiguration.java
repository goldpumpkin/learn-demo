package com.gold.zookeeper.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CuratorFrameworkConfiguration {

    @Value("${zookeeper.host}")
    private String zookeeperHost;

    @Value("${zookeeper.port}")
    private Integer zookeeperPort;

    private static final Integer CONNECTION_TIMEOUT_MS = 5000;

    private static final Integer SESSION_TIMEOUT_MS = 6000;

    private static final Integer BASE_SLEEP_MS = 1000;

    private static final Integer MAX_RETRIES = 3;

    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework() {
        final String url = zookeeperHost + ":" + zookeeperPort;
        final ExponentialBackoffRetry ebr = new ExponentialBackoffRetry(BASE_SLEEP_MS, MAX_RETRIES);
        return CuratorFrameworkFactory.newClient(url, SESSION_TIMEOUT_MS, CONNECTION_TIMEOUT_MS, ebr);
    }
}
