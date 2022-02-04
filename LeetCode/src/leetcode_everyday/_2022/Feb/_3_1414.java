package leetcode_everyday._2022.Feb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/2/3 10:07
 */
public class _3_1414 {
    /**
     * 每日一题：2022/2/3
     * <p>
     * 1414. 和为 K 的最少斐波那契数字数目
     * <p>
     * 难度：medium
     * <p>
     * 给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
     * <p>
     * 斐波那契数字定义为：
     * <p>
     * F1 = 1
     * F2 = 1
     * Fn = Fn-1 + Fn-2 ， 其中 n > 2 。
     * 数据保证对于给定的 k ，一定能找到可行解。
     * <p>
     * 输入：k = 7
     * <p>
     * 输出：2
     * <p>
     * 解释：斐波那契数字为：1，1，2，3，5，8，13，……
     * 对于 k = 7 ，我们可以得到 2 + 5 = 7 。
     * <p>
     * 范围
     * <p>
     * 1 <= k <= 10^9
     */

    /*
    思路：贪心
    将所有小于等于k的斐波那契数都加到list中
    然后减去
     */
    public int findMinFibonacciNumbers(int k) {
        int a = 1, b = 1;
        List<Integer> store = new ArrayList<>();
        store.add(a);
        while (a + b <= k) {
            int temp = a + b;
            a = b;
            b = temp;
            store.add(temp);
        }
        int res = 0;
        for (int i = store.size() - 1; i >= 0 && k > 0; i--) {
            int num = store.get(i);
            if (k >= num) {
                k -= num;
                res++;
            }
        }
        return res;


    }
}
