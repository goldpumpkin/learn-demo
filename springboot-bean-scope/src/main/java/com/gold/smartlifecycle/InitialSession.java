package com.gold.smartlifecycle;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * 目的：加载实例 - 自动建立 session 链接
 */
@Slf4j
@Component
public class InitialSession implements SmartLifecycle {

    private volatile boolean running = false;

    private void initSession() {
        // TODO: 初始化 Session
        log.info("initSession...");
    }

    private void closeSession() {
        // TODO: 关闭 Session
        log.info("closeSession...");
    }

    @Override
    public void start() {
        log.info("start...");
        if (!running) {
            initSession();
            running = true;
        }
    }

    @Override
    public void stop() {
        log.info("stop...");
        if (running) {
            closeSession();
            running = false;
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public boolean isAutoStartup() {
        // 自动启动
        return SmartLifecycle.super.isAutoStartup();
    }
}
