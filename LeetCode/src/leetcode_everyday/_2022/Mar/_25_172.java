package leetcode_everyday._2022.Mar;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2022/3/25 9:00
 */
public class _25_172 {
    /**
     * 每日一题：2022/3/25
     * <p>
     * 172. 阶乘后的零
     * <p>
     * 难度：medium
     * <p>
     * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
     * <p>
     * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
     * <p>
     * 示例1
     * <p>
     * 输入：n = 5
     *
     * 输出：1
     *
     * 解释：5! = 120 ，有一个尾随 0
     * <p>
     * 范围
     * <p>
     * 0 <= n <= 10^4
     */

    /*
   思路：数学分析
   10 可以分解成 质因数 2 和 5 的乘积
   因此结果就由分解质因数后 2的数量 和 5的数量 中的较小值
     */
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }

    @Test
    public void test1() {
        int a = 1577*2;
        int size = (a >> 3) + ((a & 0x7) != 0 ? 1 : 0);
        System.out.println(size);
    }
}
