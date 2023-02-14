package com.gold.jpa.controller;

import com.alibaba.fastjson.JSON;
import com.gold.jpa.entity.CustomerInfo;
import com.gold.jpa.repo.CustomerInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author goldhuang
 * @Description:
 * @date 2021-08-25
 */
@RequestMapping("/jpa")
@RestController
@AllArgsConstructor
public class JPANormalController {

    private final CustomerInfoRepository repository;

    /**
     * 实例的改变，加上事务，即使不 saveOrUpdate 都会保存到数据库
     */
    @Transactional
    @GetMapping("get-by-id/{id}")
    public CustomerInfo getById(@PathVariable(value = "id") Integer id) {
        Optional<CustomerInfo> entity = repository.findById(id);
        CustomerInfo customerInfo = entity.orElse(null);
        System.out.println(JSON.toJSONString(customerInfo));

        customerInfo.setCustomerName("11111");
        return customerInfo;
    }
}
