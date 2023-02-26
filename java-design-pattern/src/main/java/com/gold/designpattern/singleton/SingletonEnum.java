package com.gold.designpattern.singleton;

/**
 * @Author: GoldHuang
 * @Description: 枚举类方式单例模式 - 最精简的线程安全的单例模式
 * <p>
 * 为什么枚举实现的单例是线程安全的 ???
 * <p>
 * 枚举类在经过javac编译后，会被转换成形如 public fianl class T extends Enum 的定义
 * 而且，枚举类中的各个枚举同时通过static来定义的。如：
 * <p>
 * public fianl class T extends Enum {
 * <p>
 * public static final T INSTANCE;
 * private static final T ENUM$VALUES[];
 * <p>
 * static {
 * INSTANCE = new T("INSTANCE", 0);
 * ENUM$VALUES = (new T[] {
 * INSTANCE
 * })
 * }
 * }
 * <p>
 * 当一个java类第一次被真正使用到的时候，静态资源被初始化、java类的加载和初始化过程都是线程安全的，
 * 因为虚拟机在加载枚举的类的时候，会使用ClassLoader的loadClass方法，而这个方法使用同步代码块，保证了线程安全。
 */
public enum SingletonEnum {

    INSTANCE;

    public void doSomeThing() {

    }

    // 使用
    public static void main(String[] args) {

        SingletonEnum singletonEnum = SingletonEnum.INSTANCE;
        singletonEnum.doSomeThing();
    }
}
