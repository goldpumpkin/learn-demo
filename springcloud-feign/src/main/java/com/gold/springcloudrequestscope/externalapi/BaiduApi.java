package com.gold.springcloudrequestscope.externalapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "baiduClient", url = "www.baidu.com")
public interface BaiduApi {

    @PostMapping("/")
    String index();
}
