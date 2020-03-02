package com.gold.spring_requested_scope_data.controller;

import com.gold.spring_requested_scope_data.externalapi.BaiduApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RequestScopeDataController {

    private BaiduApi baiduApi;

    @PostMapping("/index")
    public String index() {
        return baiduApi.index();
    }
}
