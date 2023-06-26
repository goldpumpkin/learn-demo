package com.gold.prometheus;

import com.gold.prometheus.metrics.CounterMetrics;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AppController {

    private final CounterMetrics counterMetrics;

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("counter")
    public String counter(@RequestParam("sum") Integer sum) {

        for (int i = 0; i < sum; i++) {
            int mod = i % 2;
            if (mod == 0) {
                counterMetrics.record("CS", "BUY");
            } else {
                counterMetrics.record("OPT", "SELL");
            }
        }

        return "FINISH:" + sum;
    }


}
