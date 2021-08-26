package com.gold.jpa.controller;

import com.gold.jpa.crypto.CryptoUtil;
import com.gold.jpa.entity.CustomerInfo;
import com.gold.jpa.method.cstombasictype.CustomCrypto;
import com.gold.jpa.model.CryptoParam;
import com.gold.jpa.repo.CustomerInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author goldhuang
 * @Description:
 * @date 2021-08-20
 */
@RequestMapping("/jpa")
@RestController
@AllArgsConstructor
public class CryptoController {

    private final CustomerInfoRepository repository;

    @RequestMapping(value = "/test")
    public List<CustomerInfo> test() {
        return repository.findAll();
    }

    /**
     * 加密 Convert 验证
     *
     * @return
     */
    @RequestMapping(value = "/convert")
    public CustomerInfo convert() {
        String name = "gg";
        final CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerName(name);
        customerInfo.setPassword("nkdlsfk");
        customerInfo.setCustomerNameCrypto(CryptoParam.ofOriginal(name));
        final CustomerInfo save = repository.save(customerInfo);

        final CustomerInfo cc = repository.findById(save.getId()).orElse(null);
        Objects.requireNonNull(cc);

        final CryptoParam customerNameCrypto = cc.getCustomerNameCrypto();
        Assert.isTrue(customerNameCrypto.getOriginal().equals(name), "姓名不相同");
        Assert.isTrue(customerNameCrypto.getEncryption().equals(CryptoUtil.encrypt(name)), "姓名加密数据有误");

        return cc;
    }


    /**
     * 加密 basic 验证
     */
    @RequestMapping(value = "/basic")
    public CustomerInfo basicType() {
        String name = "basicGG";
        final CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerName(name);
        customerInfo.setPassword("basic");
        customerInfo.setBasicTypeName(CustomCrypto.<String>builder().original(name).build());
        final CustomerInfo save = repository.save(customerInfo);

        final CustomerInfo cc = repository.findById(save.getId()).orElse(null);
        Objects.requireNonNull(cc);

        final CustomCrypto<String> basicTypeName = cc.getBasicTypeName();
        Assert.isTrue(basicTypeName.getOriginal().equals(name), "姓名不相同");
        Assert.isTrue(basicTypeName.getEncryption().equals(CryptoUtil.encrypt(name)), "姓名加密数据有误");

        return cc;
    }


}
