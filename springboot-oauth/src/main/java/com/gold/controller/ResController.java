package com.gold.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author goldhuang
 * @Description: 资源
 * @date 2020-08-26
 */
@RestController
@AllArgsConstructor
public class ResController {

    @GetMapping("/res/{id}")
    public String testOauth(@PathVariable String id) {
        return "Get the resource " + id;
    }
}
