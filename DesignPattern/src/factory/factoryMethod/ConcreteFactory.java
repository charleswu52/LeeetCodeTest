package factory.factoryMethod;

import factory.simplefactory.Product;

/**
 * @author WuChao
 * @create 2021/7/22 下午3:53
 */


public class ConcreteFactory extends Factory {

    @Override
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}

