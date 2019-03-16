package com.gold.common.cglib_proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: GoldHuang
 * @Date: 19:45 2019/3/15
 * @Description:
 * @Version: V1.0
 */
public class TestPlusCglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long start = System.currentTimeMillis();

        Object object = methodProxy.invokeSuper(o, objects);

        long gapTime = System.currentTimeMillis() - start;

        System.out.println("[CGLIB] Test spended " + gapTime + " ms" + "\n");

        return object;
    }

}
