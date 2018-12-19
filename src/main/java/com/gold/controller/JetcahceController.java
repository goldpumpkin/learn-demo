package com.gold.controller;

import com.gold.service.JetcacheTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/jetcache")
public class JetcahceController {


    @Autowired
    private JetcacheTestService jetCacheTestService;

    /**
     * ********************************
     * remote cache 简单用法 的相关测试 *
     * ********************************
     */

    /**
     * 最基础用法
     */
    @RequestMapping(value = "/testRemoteBase", method = {RequestMethod.POST})
    public void testRemote(HttpServletResponse response) {
        System.out.println(jetCacheTestService.testRemoteBase());
    }

    /**
     * ***************
     * key 的相关测试 *
     * ***************
     */

    /**
     * cache key 测试：方法无参
     */
    @RequestMapping(value = "/testRemoteNoArgs", method = {RequestMethod.POST})
    public void testRemoteNoArgs(HttpServletResponse response) {
        System.out.println(jetCacheTestService.testRemoteNoArgs());
    }

    /**
     * cache key 测试：方法无参
     * 测试远程缓存key是一致的
     */
    @RequestMapping(value = "/testRemoteNoArgs1", method = {RequestMethod.POST})
    public void testRemoteNoArgs1(HttpServletResponse response) {
        System.out.println(jetCacheTestService.testRemoteNoArgs1());
    }

    /**
     * cache key 测试：方法有简单参数 指定参数
     */
    @RequestMapping(value = "/testRemoteWithSimpleArgs", method = {RequestMethod.POST})
    public void testRemoteWithSimpleArgs(HttpServletResponse response) {
        System.out.println(jetCacheTestService.testRemoteWithSimpleArgs("string"));

    }

    /**
     * cache key 测试：方法有自定义对象参数 指定key为自定义对象属性
     */
    @RequestMapping(value = "/testRemoteWithObjectArgsWithKey", method = {RequestMethod.POST})
    public void testRemoteWithObjectArgsWithKey(HttpServletResponse response) {
        JetcacheTestService.User user = new JetcacheTestService().new User(1, "gold");
        System.out.println(jetCacheTestService.testRemoteWithObjectArgsWithKey(user));

    }

    /**
     * cache key 测试：方法有自定义对象参数 不指定key
     */
    @RequestMapping(value = "/testRemoteWithObjectArgs", method = {RequestMethod.POST})
    public void testRemoteWithObjectArgs(HttpServletResponse response) {
        JetcacheTestService.User user = new JetcacheTestService().new User(2, "gold2");
        System.out.println(jetCacheTestService.testRemoteWithObjectArgs(user));
    }

    /**
     * cache key 测试：方法有自定义对象参数 指定key为自定义对象的多个属性
     */
    @RequestMapping(value = "/testRemoteWithObjectArgsAssignKey", method = {RequestMethod.POST})
    public void testRemoteWithObjectArgsAssignKey(HttpServletResponse response) {
        JetcacheTestService.User user = new JetcacheTestService().new User(3, "gold3");
        System.out.println(jetCacheTestService.testRemoteWithObjectArgsAssignKey(user));
    }

    /**
     * cache key 测试：当指定key有误时，缓存失效
     */
    @RequestMapping(value = "/testRemoteWithKeysErr", method = {RequestMethod.POST})
    public void testRemoteWithKeysErr(HttpServletResponse response) {
        System.out.println(jetCacheTestService.testRemoteWithKeysErr("111"));
    }

    /**
     * ******************************
     * other属性 相关测试 : condition*
     * ******************************
     */

    /**
     * condition : 表达式为true 才去缓存查询
     */
    @RequestMapping(value = "/testCondition", method = {RequestMethod.POST})
    public void testCondition(Boolean flag, HttpServletResponse response) {
        System.out.println(jetCacheTestService.testCondition(flag));
    }

    /**
     * condition1 : 表达式测试去缓存查询
     */
    @RequestMapping(value = "/testCondition1", method = {RequestMethod.POST})
    public void testCondition1(Boolean flag, HttpServletResponse response) {
        System.out.println(jetCacheTestService.testCondition1(flag));
    }

    /**
     * ******************************
     * other属性 相关测试 : condition*
     * ******************************
     */

    /**
     * PostCondition : test 返回结果
     */
    @RequestMapping(value = "/testPostCondition", method = {RequestMethod.POST})
    public void testPostCondition(Boolean flag, HttpServletResponse response) {
        //System.out.println(jetCacheTestService.testPostCondition());
    }

    /**
     * PostCondition2 : test 返回结果
     */
    @RequestMapping(value = "/testPostCondition2", method = {RequestMethod.POST})
    public void testPostCondition2(Boolean flag, HttpServletResponse response) {
        // System.out.println(jetCacheTestService.testPostCondition2().toString());
    }


    /**
     * *******************************************
     * 其他注解 相关测试 : @CachePenetrationProtect*
     * *******************************************
     */

    /**
     * CachePenetrationProtect : 缓存击穿 并发保护
     */
    @RequestMapping(value = "/testCachePenetrationProtect", method = {RequestMethod.POST})
    public void testCachePenetrationProtect(Boolean flag, HttpServletResponse response) {
        for (int i = 0; i < 100; i++) {
            new Thread("" + i) {
                @Override
                public void run() {
                    jetCacheTestService.testCachePenetrationProtect();
                }
            };

        }

    }
}
