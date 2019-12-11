package com.gold.springcloudfeign.controller;

import com.gold.springcloudfeign.externalapi.BaiduApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FeignController {

    private BaiduApi baiduApi;

    @PostMapping("/index")
    public String index() {
        return baiduApi.index();
    }
}
