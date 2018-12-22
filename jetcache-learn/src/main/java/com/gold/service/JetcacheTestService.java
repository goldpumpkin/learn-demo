package com.gold.service;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * jetcahce 测试服务类
 * @Author: GoldHuang
 */
@Service
public class JetcacheTestService {

    public static int num = 1;

    /**
     * 远程缓存 - key
     * test0 : 方法无参 noName noKey
     * key : 配置前缀 + 类名 + 方法名字 + ()[]
     * eg: btc:fss:c.t.b.f.z.JetCacheTestService.testRemote()[]
     * 方法无参数 默认 []
     *
     * @return
     */
    @Cached(expire = 60, cacheType = CacheType.REMOTE)
    public String testRemoteBase() {
        System.out.println("[testRemote] No Cache");
        String s = "testRemote-Value:【0】";
        return s;
    }

    /**
     * 远程缓存 - key
     * test1 : 方法无参
     * key : 配置前缀 + name + []
     * eg : btc:fss:baseData.test.[]
     *
     * @return
     */
    @Cached(name = "baseData.test.", expire = 60, cacheType = CacheType.REMOTE)
    public String testRemoteNoArgs() {
        System.out.println("[testRemote_NoArgs] No Cache");
        String s = "testRemoteNoArgs-Value:【1】";
        return s;
    }

    /**
     * 远程缓存 - key
     * test1.1 : 注意 与test1的key相同 目的是测试 是否会覆盖value
     * eg : btc:fss:baseData.test.[]
     *
     * @return
     */
    @Cached(name = "baseData.test.", expire = 60, cacheType = CacheType.REMOTE)
    public String testRemoteNoArgs1() {
        System.out.println("[testRemote_NoArgs1] No Cache");
        String s = "testRemoteNoArgs1-Value:【1.1】";
        return s;
    }

    /**
     * 远程缓存 - key
     * test2 : 方法有简单参数 指定key
     * key : 配置前缀 + name + 参数value
     * eg: btc:fss:baseData.test.string
     */
    @Cached(name = "baseData.test.", key = "#str", expire = 60, cacheType = CacheType.REMOTE)
    public String testRemoteWithSimpleArgs(String str) {
        System.out.println("[testRemote_WithSimpleArgs] No Cache");
        String s = "testRemoteWithSimpleArgs-Value: 【2】";
        return s;
    }

    /**
     * 远程缓存 - key
     * test3 ： 方法有自定义对象参数 指定key
     * key : btc:fss:baseData.test. + key
     * eg : btc:fss:baseData.test.1
     */
    @Cached(name = "baseData.test.", key = "#user.id", expire = 60, cacheType = CacheType.REMOTE)
    public String testRemoteWithObjectArgsWithKey(User user) {
        System.out.println("[testRemoteWithObjectArgsWithKey] No Cache");
        String s = "testRemoteWithObjectArgs-Value: 【3】 AND message: " + user.toString();
        return s;
    }

    /**
     * 远程缓存 - key
     * test4 : 方法有自定义对象参数 无指定key
     * key : btc:fss:baseData.test.[{"id":2,"name":"gold2"}]
     * eg:
     */
    @Cached(name = "baseData.test.", expire = 60, cacheType = CacheType.REMOTE)
    public String testRemoteWithObjectArgs(User user) {
        System.out.println("[testRemote_WithObjectArgs] No Cache");
        String s = "testRemoteWithObjectArgs-Value: 【4】 AND message: " + user.toString();
        return s;
    }


    /**
     * 远程缓存 - key
     * test5 : 方法有自定义对象参数 指定key
     * key : btc:fss:baseData.test.[{"id":2,"name":"gold2"}]
     * eg: btc:fss:baseData.test.3_gold3
     */
    @Cached(name = "baseData.test.", key = "#user.id + '_' + #user.name", expire = 60, cacheType = CacheType.REMOTE)
    public String testRemoteWithObjectArgsAssignKey(User user) {
        System.out.println("[testRemote_WithObjectArgsAssignKey] No Cache");
        String s = "testRemoteWithObjectArgsAssignKey-Value: 【5】 AND message: " + user.toString();
        return s;
    }

    /**
     * 远程缓存：
     * 出错时 没有缓存
     * 说明：此处的 key = "#{str}" 会导致jetcache出错，但不影响主流程，只是不会走缓存
     */
    @Cached(name = "baseData.test.", key = "#{str}", expire = 60, cacheType = CacheType.REMOTE)
    public String testRemoteWithKeysErr(String str) {
        System.out.println("[testRemote_WithKeysErr] No Cache");
        String s = "errKey";
        return s;
    }

    /**
     * ******************************
     * other属性 相关测试 : condition*
     * ******************************
     */


    /**
     * 属性condition test1
     * condition：是否进行缓存的判断条件
     */
    @Cached(name = "baseData.test.", expire = 60, condition = "#flag", cacheType = CacheType.REMOTE)
    public String testCondition(Boolean flag) {
        System.out.println("[testCondition] No Cache");
        String s = "testRemoteNoArgs-Value:【7】";
        return s;
    }

    /**
     * 属性condition test2
     * condition：是否进行缓存的判断条件
     */
    @Cached(name = "baseData.test.", expire = 60, condition = "#flag.equals(true)", cacheType = CacheType.REMOTE)
    public String testCondition1(Boolean flag) {
        System.out.println("[testCondition1] No Cache");
        String s = "testRemoteNoArgs-Value:【8】";
        return s;
    }

    /**
     * **********************************
     * other属性 相关测试 : PostCondition*
     * **********************************
     */

    /**
     * 属性 postCondition -
     * 说明：根据执行结果来判断是否需进行缓存
     */
    @Cached(name = "baseData.test.", expire = 60, postCondition = "#result.equals('1')", cacheType = CacheType.REMOTE)
    public String testPostCondition() {
        System.out.println("[testPostCondition] No Cache");
        String s = "testRemoteNoArgs-Value:【9】";
        String res = "1";
        return res;
    }

    /**
     * 属性 postCondition
     * 说明：根据执行结果来判断是否需进行缓存
     */
    @Cached(name = "baseData.test.", expire = 60, postCondition = "#result != null && #result.getId() == 1", cacheType = CacheType.REMOTE)
    public User testPostCondition2() {
        System.out.println("[testPostCondition2] No Cache");
        User user = new User();
        user.setId(1);
        return user;
    }

    /**
     * *******************************************
     * 其他注解 相关测试 : @CachePenetrationProtect*
     * *******************************************
     */

    /**
     * 注解：@CachePenetrationProtect
     * 解释: 当缓存访问未命中的情况下，对并发进行的加载行为进行保护。 当前版本实现的是单JVM内的保护，即同一个JVM中同一个key只有一个线程去加载，其它线程等待结果
     * 事例说明：开启多个线程 调用此方法， 只会有一个线程执行此方法
     */
    @CachePenetrationProtect
    @Cached(name = "baseData.test.2", expire = 60, cacheType = CacheType.REMOTE)
    public String testCachePenetrationProtect()  {
        try {
            System.out.println("[testCachePenetrationProtect] No Cache");
            String s = Thread.currentThread().getName() + "testRemoteNoArgs-Value:【9】";
            System.out.println("--------------" + s);
            Thread.sleep(10000L);
        }catch (Exception e) {

        }
        return "@CachePenetrationProtect";
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class User {

        private Integer id;

        private String name;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
