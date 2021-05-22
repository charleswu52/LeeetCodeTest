package chp8;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/5/22 上午10:11
 */
public class _13 {
    /**
     * 程序员面试金典(version 6) - 面试题 08.13. 堆箱子
     * 难度: hard
     * <p>
     * 堆箱子。给你一堆n个箱子，箱子宽 wi、深 di、高 hi。箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。
     * 实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。
     * <p>
     * 输入使用数组[wi, di, hi]表示每个箱子。
     * <p>
     * 输入：box = [[1, 1, 1], [2, 2, 2], [3, 3, 3]]
     * 输出：6
     * <p>
     * 输入：box = [[1, 1, 1], [2, 3, 4], [2, 6, 7], [3, 4, 5]]
     * 输出：10
     * <p>
     * 数据范围：
     * 箱子的数目不大于3000个。
     */

    /*
    思路：使用动态规划
     */
    public int pileBox(int[][] box) {
        int n = box.length;
        // 先对箱子进行排序
        Arrays.sort(box, (a, b) -> a[1] - b[1]); // 根据 任意维度 对箱子进行排序
        System.out.println(Arrays.deepToString(box)); // 看一下输出结果

        //创建dp 数组：dp[i]表示以第i个箱子为结尾的上升子序列的最大总高度
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = box[i][2];
        }
//        System.out.println(Arrays.toString(dp));
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (box[j][0] < box[i][0] && box[j][1] < box[i][1] && box[j][2] < box[i][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + box[i][2]);
                }
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }

    @Test
    public void test() {
        int[][] box = {
                new int[]{3, 4, 5},
                new int[]{2, 6, 7},
                new int[]{1, 1, 1},
                new int[]{2, 3, 4}
        };
        System.out.println(Arrays.deepToString(box));
        System.out.println(pileBox(box));
    }
}
