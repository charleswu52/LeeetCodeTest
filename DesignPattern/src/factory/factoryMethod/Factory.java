package factory.factoryMethod;

import factory.simplefactory.Product;

/**
 * @author WuChao
 * @create 2021/7/22 下午3:50
 */
public abstract class Factory {
    public abstract Product factoryMethod();
    public void doSomething() {
        Product product = factoryMethod();
    }
}
