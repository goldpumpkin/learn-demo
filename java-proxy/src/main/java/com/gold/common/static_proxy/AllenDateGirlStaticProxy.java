package com.gold.common.static_proxy;

/**
 * @Author: GoldHuang
 * @Description: 静态代理类
 */
public class AllenDateGirlStaticProxy implements DateGirl {

    private AllenDateGirl allenDateGirl;

    public AllenDateGirlStaticProxy(AllenDateGirl allenDateGirl) {
        this.allenDateGirl = allenDateGirl;
    }

    @Override
    public Object date() {
        buyFlowers();
        allenDateGirl.date();
        return null;
    }

    /**
     * 扩展功能：约会前买花
     */
    private void buyFlowers() {
        System.out.println("Proxy Buy 999 Roses ！！！");
    }
}
