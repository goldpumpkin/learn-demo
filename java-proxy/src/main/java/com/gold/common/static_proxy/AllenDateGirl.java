package com.gold.common.static_proxy;

/**
 * @Author: GoldHuang
 * @Description: 委托类
 */
public class AllenDateGirl implements DateGirl {

    @Override
    public Object date() {

        System.out.println("Allen Yu To DATE Girl ...");

        return null;
    }
}
