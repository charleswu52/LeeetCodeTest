package leetcode_everyday._2022.Mar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/3/6 8:55
 */
public class _6_2100 {
    /**
     * 每日一题：2022/3/6
     * <p>
     * 2100. 适合打劫银行的日子
     * <p>
     * 难度：medium
     * <p>
     * 你和一群强盗准备打劫银行。给你一个下标从 0 开始的整数数组 security ，其中 security[i] 是第 i 天执勤警卫的数量。日子从 0开始编号。
     * 同时给你一个整数 time 。
     * <p>
     * 如果第 i 天满足以下所有条件，我们称它为一个适合打劫银行的日子：
     * <p>
     * 第 i 天前和后都分别至少有 time 天。
     * 第 i 天前连续 time 天警卫数目都是非递增的。
     * 第 i 天后连续 time  天警卫数目都是非递减的。
     * 更正式的，第 i 天是一个合适打劫银行的日子当且仅当：
     * security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].
     * <p>
     * 请你返回一个数组，包含 所有 适合打劫银行的日子（下标从 0 开始）。返回的日子可以 任意 顺序排列。
     * <p>
     * 示例
     * <p>
     * 输入：security = [5,3,3,3,5,6,2], time = 2
     * <p>
     * 输出：[2,3]
     * <p>
     * 解释：
     * 第 2 天，我们有 security[0] >= security[1] >= security[2] <= security[3] <= security[4] 。
     * 第 3 天，我们有 security[1] >= security[2] >= security[3] <= security[4] <= security[5] 。
     * 没有其他日子符合这个条件，所以日子 2 和 3 是适合打劫银行的日子。
     *
     * <p>
     * 范围
     * <p>
     * 1 <= security.length <= 10^5
     * 0 <= security[i], time <= 10^5
     */

    /*
    思路：动态规划
    需要预先计算出第 i 天前警卫数目连续非递增的天数以及第 i 天后警卫数目连续非递减的天数即可判断第 i 天是否适合打劫。

     */
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int len = security.length;
        List<Integer> res = new ArrayList<>();
        int[] left = new int[len];
        int[] right = new int[len];
        for (int i = 1; i < len; i++) {
            if (security[i] <= security[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
            if (security[len - i - 1] <= security[len - i]) {
                right[len - i - 1] = right[len - i] + 1;
            }
        }
        for (int i = time; i < len - time; i++) {
            if (left[i] >= time && right[i] >= time) {
                res.add(i);
            }
        }
        return res;
    }
}
