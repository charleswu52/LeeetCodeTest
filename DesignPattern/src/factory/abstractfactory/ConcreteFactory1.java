package factory.abstractfactory;

/**
 * @author WuChao
 * @create 2021/7/22 下午4:25
 */
public class ConcreteFactory1 extends AbstractFactory {
    @Override
    AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    AbstractProductB createProductB() {
        return new ProductB1();
    }
}
