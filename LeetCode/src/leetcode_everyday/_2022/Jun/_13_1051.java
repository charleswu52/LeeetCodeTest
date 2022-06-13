package leetcode_everyday._2022.Jun;

import java.util.Arrays;

/**
 * @Author CharlesWu
 * @Create 2022/6/13 9:54
 * @Version 1.0
 * @Description
 * @Note
 */
public class _13_1051 {
    /**
     * 每日一题：2022/6/13
     * <p>
     * 1051. 高度检查器
     * <p>
     * 难度：easy
     * <p>
     * 学校打算为全体学生拍一张年度纪念照。根据要求，学生需要按照 非递减 的高度顺序排成一行。
     *
     * 排序后的高度情况用整数数组 expected 表示，其中 expected[i] 是预计排在这一行中第 i 位的学生的高度（下标从 0 开始）。
     *
     * 给你一个整数数组 heights ，表示 当前学生站位 的高度情况。heights[i] 是这一行中第 i 位学生的高度（下标从 0 开始）。
     *
     * 返回满足 heights[i] != expected[i] 的 下标数量 。
     * <p>
     * 示例
     * <p>
     * 输入：heights = [1,1,4,2,1,3]
     *
     * 输出：3
     *
     * 解释：
     *
     * 高度：[1,1,4,2,1,3]
     * 预期：[1,1,1,2,3,4]
     * 下标 2 、4 、5 处的学生高度不匹配。
     * <p>
     * 范围
     * <p>
     * 1 <= heights.length <= 100
     * 1 <= heights[i] <= 100
     */

    /*
    思路：排序
     */
    public int heightChecker(int[] heights) {
        int res = 0, len = heights.length;
        int[] sorted = Arrays.copyOf(heights,len);
        Arrays.sort(sorted);
        for (int i = 0; i < len; i++) {
            if (sorted[i] != heights[i]) {
                res++;
            }
        }
        return res;
    }
}
