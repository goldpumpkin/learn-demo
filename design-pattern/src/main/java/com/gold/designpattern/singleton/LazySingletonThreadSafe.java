package com.gold.designpattern.singleton;

/**
 * @Author: GoldHuang
 * @Description: 懒汉式单例模式 - 线程安全 synchronized
 */
public class LazySingletonThreadSafe {

    private static LazySingletonThreadSafe instance = null;

    /**
     * 私有化构造方法
     */
    private LazySingletonThreadSafe() {

    }

    /**
     * synchronized 保证线程安全
     */
    public static synchronized LazySingletonThreadSafe getInstance() {
        if (instance == null) {
            instance = new LazySingletonThreadSafe();
        }

        return instance;
    }
}
