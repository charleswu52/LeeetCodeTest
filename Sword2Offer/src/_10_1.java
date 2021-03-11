

/**
 * @author WuChao
 * @since 2021/3/11 上午9:59
 */
public class _10_1 {
    /**
     * 剑指 Offer 10- I. 斐波那契数列
     * 难度: easy
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     * <p>
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * <p>
     * 示例：
     * 输入：n = 2
     * 输出：1
     * <p>
     * 数据范围：
     * 0 <= n <= 100
     */


    // 迭代算法
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] store = new int[n + 1];
        store[0] = 0;
        store[1] = 1;
        for (int i = 2; i <= n; i++) {
            store[i] = (store[i - 1] + store[i - 2]) % 1000000007;

        }
        return store[n];
    }

    // 递归实现 ==> 超时
    public int fib2(int n) {
        if (n < 2) {
            return n;
        }
        return (fib2(n - 1) + fib2(n - 2)) % 1000000007;
    }

    // 递归实现的改进，避免重复计算进行记忆化存储

    /**
     * 最优解：动态规划
     * 斐波那契的公式相当于就给出了状态转移方程，而且也给出了初始状态
     */
    public int fib4(int n) {
        int a = 0, b = 1, sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }


    public void sword2Offer_10_1() {
        System.out.println(fib(100));
        System.out.println(fib4(100));

    }
}
