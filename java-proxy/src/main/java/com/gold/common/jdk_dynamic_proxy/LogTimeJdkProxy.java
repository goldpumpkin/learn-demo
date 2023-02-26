package com.gold.common.jdk_dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: GoldHuang
 * @Description: jdk dynamic proxy 实现时间统计代理
 */
public class LogTimeJdkProxy implements InvocationHandler {

    /**
     * 目标对象
     */
    private Object target;

    /**
     * 关联委托类
     *
     * @param target
     */
    public LogTimeJdkProxy(Object target) {
        this.target = target;
    }

    /**
     * 绑定关系
     *
     * @return 返回代理类
     */
    public Object bind() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 委托类的方法被调用时 将执行此方法
     *
     * @param proxy  生成的代理类对象
     * @param method 委托类调用的方法名称
     * @param args   该方法参数
     * @return 返回执行结果
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        long start = System.currentTimeMillis();

        // 执行原方法
        result = method.invoke(target, args);

        long gapTime = System.currentTimeMillis() - start;

        System.out.println("[JDK] Test spended " + gapTime + " ms" + "\n");

        return result;
    }


}
