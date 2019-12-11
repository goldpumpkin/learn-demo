package src;

/**
 * @Author: GoldHuang
 * @Description: 饿汉式单例模式
 */
public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    /**
     * 私有化构造方法
     */
    public static HungrySingleton getInstance() {
        return instance;
    }
}
