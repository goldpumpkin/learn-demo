package model.decorator;

import model.Component;

/**
 * @Author: GoldHuang
 * @Date: 10:31 2019/3/27
 * @Description: 装饰类
 */
public abstract class Decorator implements Component {

    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void Operation() {
        if (component != null) {
            component.Operation();
        }
    }
}
