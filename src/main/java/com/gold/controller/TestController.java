package com.gold.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: GoldHuang
 * @Date: 19:01 2018/12/10
 * @Description:
 * @Version: V1.0
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println("1111111111");
        return "test!!!!";
    }

}
