package com.gold.designpattern.singleton;

/**
 * @Author: GoldHuang
 * @Description: 懒汉式单例模式 - 非线程安全
 */
public class LazySingleton {

    private static LazySingleton instance = null;

    /**
     * 私有化构造方法
     */
    private LazySingleton() {

    }

    public static LazySingleton getInstance() {

        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }
}
