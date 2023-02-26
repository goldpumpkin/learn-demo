package com.gold.designpattern.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: GoldHuang
 * @Description: 容器类单例模式
 */
public class SingletonManager {

    private static Map<String, Object> objectMap = new HashMap<String, Object>();

    private SingletonManager() {
    }

    public static void registerService(String key, Object instance) {
        if (!objectMap.containsKey(key)) {
            objectMap.put(key, instance);
        }
    }

    public static Object getObjectService(String key) {
        return objectMap.get(key);
    }

}
