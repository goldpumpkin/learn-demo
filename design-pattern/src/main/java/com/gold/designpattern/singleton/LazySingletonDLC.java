package com.gold.designpattern.singleton;

/**
 * @Author: GoldHuang
 * @Description: 懒汉式单例模式 - Double Check Lock (DCL)
 *
 * 这种单例模式也是不能够保证线程安全的
 * 原因：
 *  1. 对象的创建的过程不是原子的，具体过程：
 *     // 1. 为Singleton对象分配内存
 *     memory = allocate();
 *     // 2. 变量指向内存地址，注意现在instance是非空的，但还有初始化
 *     instance = memory
 *     // 3. 调用Singleton的构造函数，传递instance
 *     ctorSingleton(instance);
 *
 *   具体描述：
 *      当线程一执行到步骤2，线程二判断instance是否为null,返回的instance，并不是真正的实例对象，
 *      如果此时线程2使用此对象则可能会抛错
 *
 *  2. 并且这三个指令的先后顺序不是确定的。
 */
public class LazySingletonDLC {

    /**
     * 不会被本地线程缓存，所有对该变量的读写都是直接操作共享内存，从而确保多个线程能正确处理该变量
     */
    private volatile static LazySingletonDLC instance;

    /**
     * 1. 私有化构造方法
     */
    private LazySingletonDLC() {

    }

    /**
     * 2. DCL
     */
    public static LazySingletonDLC getInstance() {

        if (instance == null) {
            synchronized (LazySingletonDLC.class) {
                if (instance == null) {
                    instance = new LazySingletonDLC();
                }
            }
        }

        return instance;
    }
}
