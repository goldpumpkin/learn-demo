package com.gold.prometheus.metrics;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.Counter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CounterMetrics implements InitializingBean {

    private static final String REQUEST_COUNT = "place_order_request";

    private Counter placeOrderReqCounter;

    @Autowired
    private PrometheusMeterRegistry prometheusMeterRegistry;

    @Override
    public void afterPropertiesSet() throws Exception {
        placeOrderReqCounter = Counter.build(REQUEST_COUNT, "The number of placing order request")
                .labelNames("securityType", "side")
                .register(prometheusMeterRegistry.getPrometheusRegistry());
   }

    public void record(String securityType, String side) {
        try {
            placeOrderReqCounter.labels(securityType, side).inc();
        } catch (Exception e) {
            log.error("record orderReqLatency error", e);
        }
    }

}
