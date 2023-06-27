package com.gold.prometheus.metrics;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.Histogram;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HistogramMetrics implements InitializingBean {

    private static final String REQUEST_LATENCY = "request_latency";

    private Histogram requestLatency;

    @Autowired
    private PrometheusMeterRegistry prometheusMeterRegistry;

    @Override
    public void afterPropertiesSet() throws Exception {
        requestLatency = Histogram.build(REQUEST_LATENCY, "request latency")
                .labelNames("place_order_request")
                .buckets(0.1, 0.5, 1.0, 2.0, 5.0, 10.0)
                .register(prometheusMeterRegistry.getPrometheusRegistry());
    }

    public void record(double duration) {
        try {
            requestLatency.labels("place_order_request").observe(duration);
        } catch (Exception e) {
            log.error("log place_order_request error", e);
        }
    }

}
