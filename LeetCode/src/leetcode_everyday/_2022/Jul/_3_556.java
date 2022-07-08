package leetcode_everyday._2022.Jul;

import java.util.LinkedList;

/**
 * @author WuChao
 * @create 2022/7/8 14:33
 */
public class _3_556 {
    /**
     * 每日一题：2022/7/3
     * <p>
     * 556. 下一个更大元素 III
     * <p>
     * 难度：medium
     * <p>
     * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
     * <p>
     * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
     * <p>
     * 示例
     * <p>
     * 输入：n = 12
     * <p>
     * 输出：21
     * <p>
     * 范围
     * <p>
     * 1 <= n <= 2^31 - 1
     */

    /*
    思路：
    下一个排列
    实际上是求字符串的下一个排列
     */
    public int nextGreaterElement(int n) {
        if (n == Integer.MAX_VALUE) return -1;
        LinkedList<Integer> t = new LinkedList<>();
        while (n != 0) {
            t.addFirst(n % 10);
            n = n / 10;
        }

        for (int i = t.size() - 2; i >= 0; --i) {
            if (t.get(i) < t.get(i + 1)) {
                //调整i位置即可
                //向后找比i大的 最小的数的位置 交换
                int targetIndex = i + 1;
                for (int j = i + 1; j < t.size(); ++j) {
                    if (t.get(j) > t.get(i) && t.get(j) <= t.get(targetIndex)) {
                        targetIndex = j;
                    }
                }
                swap(t, i, targetIndex);

                //调整从i+1开始到结尾的顺序 [i+1,t.size()-1] 依次递增 保证最小
                int l = i + 1, r = t.size() - 1;
                while (l < r) {
                    swap(t, l++, r--);
                }


                //判断一下调整完之后是不是越界了
                long res = 0;
                for (int k = 0; k < t.size(); ++k) {
                    res = res * 10 + t.get(k);
                }
                if (res > Integer.MAX_VALUE) return -1;

                return (int) res;

            }
        }
        return -1;
    }

    private void swap(LinkedList<Integer> t, int a, int b) {
        int tmp = t.get(a);
        t.set(a, t.get(b));
        t.set(b, tmp);
    }

}
