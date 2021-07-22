package jd;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/7/21 18:17
 */
public class Singleton {
    private volatile static Singleton singleton;
    private Singleton() {

    }

    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    @Test
    public void test() {

    }
}
