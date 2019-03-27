package model.decorator;

/**
 * @Author: GoldHuang
 * @Date: 10:30 2019/3/27
 * @Description:
 */
public class ConcreteDecoratorA extends Decorator {

    /**
     * 本类独有功能
     */
    private String addedState;

    @Override
    public void Operation() {
        super.Operation();
        addedState = "New State";
        System.out.println("ConcreteDecorator A operation");
    }
}
