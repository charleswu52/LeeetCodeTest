package leetcode_everyday._2022.Apr;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2022/4/5 9:03
 */
public class _5_762 {
    /**
     * 每日一题：2022/4/5
     * <p>
     * 762. 二进制表示中质数个计算置位
     * <p>
     * 难度：easy
     * <p>
     * 给你两个整数 left 和 right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。
     *
     * 计算置位位数 就是二进制表示中 1 的个数。
     *
     * 例如， 21 的二进制表示 10101 有 3 个计算置位。
     * <p>
     * 示例
     * <p>
     * 输入：left = 6, right = 10
     *
     * 输出：4
     *
     * 解释：
     * 6 -> 110 (2 个计算置位，2 是质数)
     * 7 -> 111 (3 个计算置位，3 是质数)
     * 9 -> 1001 (2 个计算置位，2 是质数)
     * 10-> 1010 (2 个计算置位，2 是质数)
     * 共计 4 个计算置位为质数的数字。
     *
     * 范围
     * <p>
     * 1 <= left <= right <= 10^6
     * 0 <= right - left <= 10^4
     */

    /*
    思路：位运算
     */
    public int countPrimeSetBits(int left, int right) {
        Set<Integer> set = new HashSet<>();
        set.add(2);
        set.add(3);
        set.add(5);
        set.add(7);
        set.add(11);
        set.add(13);
        set.add(17);
        set.add(19);
        int ans = 0;
        for (int i = left; i <= right; i++) {
            int count = 0, temp = i;
            while (temp != 0) {
                count += temp & 1;
                temp >>= 1;
            }
            if (set.contains(count)) {
                ans++;
            }
        }
        return ans;

    }

    @Test
    public void test() {
        int left = 10, right = 15;
        System.out.println(countPrimeSetBits(left, right));
    }

}
