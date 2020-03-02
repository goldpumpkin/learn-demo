package com.gold.spring_requested_scope_data.externalapi;

import com.gold.spring_requested_scope_data.config.BaiduFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "baiduClient", url = "www.baidu.com", configuration = {BaiduFeignConfig.class})
public interface BaiduApi {

    @PostMapping("/")
    String index();
}
