package com.gold.common.base;

import com.gold.common.base.animal.Animal;
import com.gold.common.base.animal.Dog;
import com.gold.common.base.runner.Runner;
import com.gold.common.base.test.EnglishTest;
import com.gold.common.base.test.TestExec;
import com.gold.common.cglib_proxy.TestPlusCglibProxy;
import com.gold.common.jdk_dynamic_proxy.LogTimeJdkProxy;
import com.gold.common.static_proxy.AllenDateGirl;
import com.gold.common.static_proxy.AllenDateGirlStaticProxy;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @Author: GoldHuang
 * @Description: case 执行类
 */
public class CaseExec {

    public static void main(String[] args) throws Exception{

        /** 静态代理 **/
        System.out.println("------------- [static proxy] Plus ----------------");
        staticProxy();

        /** 动态代理 **/
        System.out.println("------------- [JDK proxy] Plus ----------------");

        // jdk_dynamic_proxy : case 1
        jdkProxySimple();

        // jdk_dynamic_proxy : case 2
        jdkProxySimple2();

        // jdk_dynamic_proxy : case 3
        jdkProxyTestNoInterface();


        System.out.println("------------- [CGLIB proxy] Plus ----------------");

        // cglib_dynamic_proxy : case 1
        cglibProxySimple();

        // cglib_dynamic_proxy : case 2
        cglibProxySimple2();

        // cglib_dynamic_proxy : case 3
        cglibProxyTestNoInterface();


    }

    /**
     * 静态代理类
     */
    public static void staticProxy() {
        // Allen Date
        new AllenDateGirlStaticProxy(new AllenDateGirl()).date();
    }

    /**
     * jdk_proxy case 1 : simple case
     */
    public static void jdkProxySimple() throws Exception{
        TestExec testJDKPlus = (TestExec) new LogTimeJdkProxy(new EnglishTest()).bind();
        testJDKPlus.test();
    }

    /**
     * jdk_proxy case 2 : simple case 2
     * remark：说明和静态代理的不同
     */
    public static void jdkProxySimple2() throws Exception{
        Animal animal = (Animal) new LogTimeJdkProxy(new Dog()).bind();
        animal.eat();
    }

    /**
     * jdk_proxy case 3 : 只能对实现了接口的类进行代理
     */
    public static void jdkProxyTestNoInterface() {
        try {
            Runner runner = (Runner) new LogTimeJdkProxy(new Runner()).bind();
            runner.run();
        }catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * cglib_proxy case 1 : simple case
     */
    public static void cglibProxySimple() throws Exception{
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnglishTest.class);
        enhancer.setCallback(new TestPlusCglibProxy());

        TestExec testEnglishCGLIBExec = (TestExec)enhancer.create();
        testEnglishCGLIBExec.test();
    }

    /**
     * cglib_proxy case 2 : simple case
     */
    public static void cglibProxySimple2() throws Exception{
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        enhancer.setCallback(new TestPlusCglibProxy());

        Animal animal = (Animal)enhancer.create();
        animal.eat();
    }

    /**
     * cglib_proxy case 3 : 对指定的类生成一个子类，覆盖其中的方法.
     */
    public static void cglibProxyTestNoInterface() throws Exception{
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Runner.class);
        enhancer.setCallback(new TestPlusCglibProxy());
        Runner runner = (Runner)enhancer.create();
        runner.run();
    }

    private static TestExec adaptdiffTest(Class clz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(new TestPlusCglibProxy());
        TestExec exec = (TestExec)enhancer.create();
        return exec;
    }

}
