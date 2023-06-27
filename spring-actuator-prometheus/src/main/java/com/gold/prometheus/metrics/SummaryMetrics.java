package com.gold.prometheus.metrics;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.Summary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SummaryMetrics implements InitializingBean {

    private static final String REQUEST_LATENCY = "summary_request_latency";

    private Summary requestLatencySummary;

    @Autowired
    private PrometheusMeterRegistry prometheusMeterRegistry;

    @Override
    public void afterPropertiesSet() throws Exception {
        requestLatencySummary = Summary.build(REQUEST_LATENCY, "summary request latency")
                .labelNames("place_order_request")
                .quantile(0.5, 0.05)
                .quantile(0.9, 0.01)
                .quantile(0.99, 0.001)
                .register(prometheusMeterRegistry.getPrometheusRegistry());
    }

    public void record(double duration) {
        try {
            requestLatencySummary.labels("place_order_request").observe(duration);
        } catch (Exception e) {
            log.error("summary_request_latency error", e);
        }
    }
}
