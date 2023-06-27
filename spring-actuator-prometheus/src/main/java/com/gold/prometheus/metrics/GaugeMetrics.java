package com.gold.prometheus.metrics;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.Gauge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GaugeMetrics implements InitializingBean {

    private static final String TEMPERATURE = "temperature";

    private Gauge temperatureGauge;

    @Autowired
    private PrometheusMeterRegistry prometheusMeterRegistry;

    @Override
    public void afterPropertiesSet() throws Exception {
        temperatureGauge = Gauge.build(TEMPERATURE, "temperature")
                .labelNames("body")
                .register(prometheusMeterRegistry.getPrometheusRegistry());
    }

    /**
     * 记录温度
     */
    public void logTemperature(double temperature) {
        try {
            temperatureGauge.labels("body").set(temperature);
        } catch (Exception e) {
            log.error("log temperature error", e);
        }
    }

}
