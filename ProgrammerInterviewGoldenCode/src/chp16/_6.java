package chp16;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/5/28 上午10:25
 */
public class _6 {
    /**
     * 程序员面试金典(version 6) - 面试题 16.06. 最小差
     * 难度: medium
     * <p>
     * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
     * <p>
     * 示例:
     * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
     * 输出：3，即数值对(11, 8)
     *
     * <p>
     * 数据范围：
     * 1 <= a.length, b.length <= 100000
     * -2147483648 <= a[i], b[i] <= 2147483647
     * 正确结果在区间 [0, 2147483647] 内
     */

    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        // 开始merge排序
        int i = 0, j = 0;
        long res = Long.MAX_VALUE;
        while (i < a.length && j < b.length) {
            res = Math.min(res, Math.abs((long)a[i] - (long) b[j]));
            if (a[i] <= b[j]) {
                i++;
            } else {
                j++;
            }
        }

        return (int) res;
    }

    @Test
    public void test() {
        int[] a = {-2147483648,1};
        int[] b = {2147483647,0};
        System.out.println(smallestDifference(a, b));
    }
}
