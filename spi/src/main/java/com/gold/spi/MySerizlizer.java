package com.gold.spi;

/**
 * @Author: GoldHuang
 * @Date: 17:44 2019/7/16
 * @Description:
 */
public class MySerizlizer implements ObjectSerializer {

    @Override
    public String getSchemeName() {
        System.out.println("[MySerizlizer]");
        return null;
    }
}
