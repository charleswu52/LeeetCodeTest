package leetcodetest.Jan;

public class _4 {
    /**
     * 每日一题：2021/1/4
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给你 n ，请计算 F(n)
     */
    public static int fib(int n) {
        int[] store = new int[31];
        store[0] = 0;
        store[1] = 1;
        if (n < 2) {
            return store[n];
        }
        for (int i = 2; i <= n; i++) {
            store[i] = store[i - 1] + store[i - 2];
        }
        return store[n];
    }

    /*
    降低空间复杂度的方法
     */
    public static int fib2(int n) {
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, res = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = res;
            res = p + q;
        }
        return res;

    }


    public static void _21_1_4() {
        int n = 30;
        System.out.println(fib2(n));

    }

}
