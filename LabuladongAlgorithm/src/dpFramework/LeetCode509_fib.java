package dpFramework;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/14 下午2:49
 */
public class LeetCode509_fib {
    /**
     * 学习DP框架的例子
     * 509. 斐波那契数
     * 难度: easy
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给你 n ，请计算 F(n) 。
     * <p>
     * 数据范围：
     * 0 <= n <= 30
     */

    // 暴力递归
    public int fib1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    // 带备忘录的递归解法
    int[] store;
    public int fib2(int n) {
        if (n < 1) {
            return 1;
        }
        // 初始化备忘录
        this.store = new int[n + 1];
        Arrays.fill(store, 0);

        return helper(store, n);

    }

    public int helper(int[] store, int n) {
        // base case
        if (n == 1 || n == 2) {
            return 1;
        }
        if (store[n] != 0) {
            return store[n];
        }
        store[n] = helper(store, n - 1) + helper(store, n - 2);
        return store[n];
    }

    // 使用dp数组的解法
    public int fib3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 0);
        // base case
        dp[1] = dp[2] = 1;
        for (int i = 3; i <=n ; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 使用状态压缩的dp解法

    public int fib4(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        // 不再使用数组存储所有的状态，只使用两个来存，压缩dp数组的大小
        int pre = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = pre + curr;
            pre = curr;
            curr = sum;
        }
        return curr;
    }

    public static void main(String[] args) {
        int n = 20;
        LeetCode509_fib test = new LeetCode509_fib();
        System.out.println(test.fib1(n));
        System.out.println(test.fib2(n));
        System.out.println(test.fib3(n));
        System.out.println(test.fib4(n));

    }
}