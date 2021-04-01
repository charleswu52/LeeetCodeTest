import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/1 上午9:58
 */
public class _64 {
    /**
     * 剑指 Offer 64. 求1+2+…+n
     * 难度: medium
     * <p>
     * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     * <p>
     * 例如：
     * 输入: n = 9
     * 输出: 45
     *
     * <p>
     * 数据范围：
     * 1 <= n <= 10000
     */

     /*
     题意解析：
     本题描述看起来简单易懂，累加求和，但是难点在于对解题方法的限制
      */

    /**
     * 方法1：递归短路
     * 比较巧妙的思路
     */
    public int sumNums(int n) {
        int sum = n;
        // 巧妙利用 A && B 的短路性质，当A为false时，B的值就不会再计算
        // 设置辅助boolean变量，没有特殊的意义只是为了不报错而且使得递归可以进行
        boolean flag = n > 0 && (sum += sumNums(n - 1)) > 0; // 利用"短路"特性 当n大于0时 就继续递归 否则停止递归 return 前面的累加值
        return sum;
    }

    @Test
    public void test() {
        int n = 10;
        System.out.println(sumNums(n));

    }
}
