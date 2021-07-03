package problems;

/**
 * @author WuChao
 * @create 2021/7/2 20:06
 */
class Base
{
    public void method()
    {
        System.out.println("Base");


    }
}
class Son extends Base
{
    public void method()
    {
        System.out.println("Son");
    }

    public void methodB()
    {
        System.out.println("SonB");
    }
}
public class Test {
    public static void main(String[] args) {
        byte a = 123;
        byte b = 127;
        a+=b;
        Base base = new Son();
        base.method();

        String as = "s";
        char s = '\u2303';

        System.out.println(shu());
        System.out.println(Math.ceil(-0.2));

        Object sd = 'd';

    }


    public static int  shu() {
        try {
            return 0;
        }finally {
            return 1;
        }

    }

    static class C {
        public static void sji(){}

    }
}
