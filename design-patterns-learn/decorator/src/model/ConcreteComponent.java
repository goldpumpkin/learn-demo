package model;

/**
 * @Author: GoldHuang
 * @Date: 10:28 2019/3/27
 * @Description: 具体的对象
 */
public class ConcreteComponent implements Component {

    @Override
    public void Operation() {
        System.out.println("original object execute operation");
    }
}
