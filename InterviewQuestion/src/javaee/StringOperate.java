package javaee;

/**
 * @author WuChao
 * @create 2021/6/19 10:25
 */
public class StringOperate {

    /**
     * 字符串拼接题目
     * 相同值比较地址是否相同，两种情况下才会相等：
     *  1. 纯 "" + ""+ ""+ 的形式
     *  2. 特别的 final修饰的字符串 也是一种常量 final String == "",参与 + 运算结果地址值也是相同的
     */

    public static String getA() {
        return "a";
    }



    public static void main(String[] args) {
        String com = "ab";
        String a = "a";
        String b = "b";
        final String s = "a";
        final String t = "b";
        String c = a + b;
        String d = a + "b";
        String e = "a" + "b";
        String f = getA() + "b";
        String g = s + "b";
        String h = s + b;
        String i = s + t;
        System.out.println(c == com);  // false
        System.out.println(d == com);  // false
        System.out.println(e == com);  // true
        System.out.println(f == com);  // false
        System.out.println(g == com);  // true
        System.out.println(h == com);  // false
        System.out.println(i == com);  // true

    }

}
