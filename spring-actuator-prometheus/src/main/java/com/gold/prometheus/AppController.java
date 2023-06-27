package com.gold.prometheus;

import cn.hutool.core.util.RandomUtil;
import com.gold.prometheus.metrics.CounterMetrics;
import com.gold.prometheus.metrics.GaugeMetrics;
import com.gold.prometheus.metrics.HistogramMetrics;
import com.gold.prometheus.metrics.SummaryMetrics;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;

@Slf4j
@RestController
@AllArgsConstructor
public class AppController {

    private final CounterMetrics counterMetrics;
    private final GaugeMetrics gaugeMetrics;
    private final HistogramMetrics histogramMetrics;
    private final SummaryMetrics summaryMetrics;

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("counter")
    public String counter(@RequestParam("sum") Integer sum) {

        for (int i = 0; i < sum; i++) {
            int mod = i % 2;
            if (mod == 0) {
                counterMetrics.incPlaceOrderReq("CS", "BUY");
            } else {
                counterMetrics.incPlaceOrderReq("OPT", "SELL");
            }
        }

        return "FINISH:" + sum;
    }

    @GetMapping("gauge")
    public String logTemperature() throws InterruptedException {
        int count = 100;

        int start = 0;
        while (start < count) {
            start++;

            final double t = RandomUtil.randomDouble(-100.00, 100.00);
            gaugeMetrics.logTemperature(t);

            Thread.sleep(500L);
        }

        return "FINISH";
    }

    @GetMapping("histogram")
    public String requestLatency() {

        int bucket_0_1 = 0;
        int bucket_0_5 = 0;
        int bucket_1_0 = 0;
        int bucket_2_0 = 0;
        int bucket_5_0 = 0;
        int bucket_10_0 = 0;

        for (int i = 0; i < 100; i++) {
            final double v = RandomUtil.randomDouble(0, 10, 2, RoundingMode.HALF_UP);
            log.info("request latency duration: {}", v);

            if (v <= 0.1) {
                bucket_0_1++;
                log.info("request latency duration_0.1 bucket: {}", v);
            } else if (v <= 0.5) {
                bucket_0_5++;
                log.info("request latency duration_0.5 bucket: {}", v);
            } else if (v <= 1.0) {
                bucket_1_0++;
                log.info("request latency duration_1.0 bucket: {}", v);
            } else if (v <= 2.0) {
                bucket_2_0++;
                log.info("request latency duration_2.0 bucket: {}", v);
            } else if (v <= 5.0) {
                bucket_5_0++;
                log.info("request latency duration_5.0 bucket: {}", v);
            } else if (v <= 10.0) {
                bucket_10_0++;
                log.info("request latency duration_10.0 bucket: {}", v);
            }

            histogramMetrics.record(v);
        }

        log.info("bucket_0_1:{}, bucket_0_5:{}, bucket_1_0:{}, bucket_2_0:{}, bucket_5_0:{}, bucket_10_0:{},",
                bucket_0_1, bucket_0_5, bucket_1_0, bucket_2_0, bucket_5_0, bucket_10_0);

        return "FINISH";
    }

    @GetMapping("summary")
    public String summary() {

        for (int i = 0; i < 100; i++) {
            final double v = RandomUtil.randomDouble(0, 10, 2, RoundingMode.HALF_UP);
            log.info("request latency duration: {}", v);

            if (v <= 0.1) {
                log.info("request latency duration_0.1 bucket: {}", v);
            } else if (v <= 0.5) {
                log.info("request latency duration_0.5 bucket: {}", v);
            } else if (v <= 1.0) {
                log.info("request latency duration_1.0 bucket: {}", v);
            } else if (v <= 2.0) {
                log.info("request latency duration_2.0 bucket: {}", v);
            } else if (v <= 5.0) {
                log.info("request latency duration_5.0 bucket: {}", v);
            } else if (v <= 10.0) {
                log.info("request latency duration_10.0 bucket: {}", v);
            }

            summaryMetrics.record(v);
        }

        return "FINISH";
    }

}
