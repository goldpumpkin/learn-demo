package model;

import model.decorator.ConcreteDecoratorA;
import model.decorator.ConcreteDecoratorB;

/**
 * @Author: GoldHuang
 * @Date: 11:04 2019/3/27
 * @Description:
 */
public class TestEnter {

    public static void main(String[] args) {

        ConcreteComponent object = new ConcreteComponent();
        ConcreteDecoratorA decoratorA = new ConcreteDecoratorA();
        ConcreteDecoratorB decoratorB = new ConcreteDecoratorB();

        // 使用 装饰A
        decoratorA.setComponent(object);

        // 使用 装饰A 装饰B
        decoratorB.setComponent(decoratorA);

        decoratorB.Operation();

    }
}
