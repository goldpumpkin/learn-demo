package com.gold.springcloudrequestscope.feignlog;

import feign.Logger;
import feign.Request;
import feign.Response;

import java.io.IOException;

public class InfoFeignLogger extends Logger {

    private final org.slf4j.Logger logger;

    public InfoFeignLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        if (logger.isInfoEnabled()) {
            this.logger.info(String.format(methodTag(configKey) + format, args));
        }
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        if (this.logger.isInfoEnabled()) {
            super.logRequest(configKey, logLevel, request);
        }

    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        return this.logger.isInfoEnabled() ? super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime) : response;
    }
}
