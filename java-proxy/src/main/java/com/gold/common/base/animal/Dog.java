package com.gold.common.base.animal;

/**
 * @Author: GoldHuang
 * @Description:
 */
public class Dog implements Animal {
    @Override
    public void eat() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("dog eat ....");
    }
}
