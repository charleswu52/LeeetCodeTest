package factory.abstractfactory;

/**
 * @author WuChao
 * @create 2021/7/22 下午4:26
 */
public class ConcreteFactory2 extends AbstractFactory {

    @Override
    AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    AbstractProductB createProductB() {
        return new ProductB2();
    }
}
