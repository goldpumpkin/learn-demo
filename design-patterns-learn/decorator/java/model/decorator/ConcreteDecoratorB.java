package model.decorator;

/**
 * @Author: GoldHuang
 * @Date: 10:30 2019/3/27
 * @Description:
 */
public class ConcreteDecoratorB extends Decorator {

    @Override
    public void Operation() {
        super.Operation();
        behavorB();
        System.out.println("ConcreteDecorator B operation");
    }

    private void behavorB() {
        System.out.println("本类独有方法");
    }
}
