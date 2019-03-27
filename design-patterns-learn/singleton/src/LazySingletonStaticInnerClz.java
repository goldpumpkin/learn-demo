package src;

/**
 * @Author: GoldHuang
 * @Description: 懒汉式单例 静态内部类实现 线程安全
 *
 *  为什么是线程安全的呢？
 *      1. 加载类 LazySingletonStaticInnerClz 时，其静态内部类并不会加载；
 *      2. 当调用getInstance方法时，class loader会加载静态内部类，并执行instance的初始化。
 *       关键点就在这里，静态内部类只会加载一次，并且是单线程加载。它的并发安全是JVM保证的。
 */

public class LazySingletonStaticInnerClz {

    private static LazySingletonStaticInnerClz instance;

    private LazySingletonStaticInnerClz() {
    }

    private static class SingletonWapper{
        static LazySingletonStaticInnerClz instance = new LazySingletonStaticInnerClz();
    }

    public static LazySingletonStaticInnerClz getInstance() {
        return SingletonWapper.instance;
    }

}
