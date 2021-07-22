package factory.factoryMethod;

import factory.simplefactory.Product;

/**
 * @author WuChao
 * @create 2021/7/22 下午4:11
 */
public class ConcreteFactory1 extends Factory {
    @Override
    public Product factoryMethod() {
        return new ConcreteProduct1();
    }
}
