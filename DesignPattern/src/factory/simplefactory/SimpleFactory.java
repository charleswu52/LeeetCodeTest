package factory.simplefactory;

/**
 * @author WuChao
 * @create 2021/7/22 下午3:27
 */

class ConcreteProduct1 implements Product {

}

class ConcreteProduct2 implements Product {

}

class ConcreteProduct3 implements Product {

}

class ConcreteProduct implements Product {

}

public class SimpleFactory {
    public Product createProduct(int type) {
        switch (type) {
            case 1:
                return new ConcreteProduct1();
            case 2:
                return new ConcreteProduct2();
            case 3:
                return new ConcreteProduct3();
        }
        return new ConcreteProduct();
    }
}
