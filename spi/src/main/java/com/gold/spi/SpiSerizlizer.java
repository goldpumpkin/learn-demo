package com.gold.spi;

/**
 * @Author: GoldHuang
 * @Date: 17:46 2019/7/16
 * @Description:
 */
public class SpiSerizlizer implements ObjectSerializer {
    @Override
    public String getSchemeName() {
        System.out.println("[SpiSerizlizer]");
        return null;
    }
}
