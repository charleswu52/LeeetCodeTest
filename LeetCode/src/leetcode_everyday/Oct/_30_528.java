package leetcode_everyday.Oct;

import java.util.Arrays;
import java.util.logging.XMLFormatter;

/**
 * @author WuChao
 * @create 2021/8/30 9:03
 */
public class _30_528 {
    /**
     * 每日一题：2021/8/30
     * 528. 按权重随机选择
     * 难度：medium
     * <p>
     * 给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，
     * 它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。
     *
     * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
     *
     * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
     *
     *
     * <p>
     * 输入：
     * ["Solution","pickIndex"]
     * [[[1]],[]]
     * 输出：
     * [null,0]
     * 解释：
     * Solution solution = new Solution([1]);
     * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
     *
     *
     * <p>
     * 1 <= w.length <= 10000
     * 1 <= w[i] <= 10^5
     * pickIndex 将被调用不超过 10000 次
     */

    /*
    思路：前缀 + 二分查找
    设数组 w 的权重之和为 total。根据题目的要求，我们可以看成将 [1,total] 范围内的所有整数分成 n 个部分（其中 n 是数组 w 的长度），
    第 i 个部分恰好包含 w[i] 个整数，并且这 n 个部分两两的交集为空。随后我们在 [1,total] 范围内随机选择一个整数 x，
    如果整数 x 被包含在第 i 个部分内，我们就返回 i。
    一种较为简单的划分方法是按照从小到大的顺序依次划分每个部分。例如 w = [3, 1, 2, 4] 时，权重之和 total=10，
    那么我们按照 [1,3],[4,4],[5,6],[7,10] 对 [1, 10] 进行划分，使得它们的长度恰好依次为 3, 1, 2, 4。
    可以发现，每个区间的左边界是在它之前出现的所有元素的和加上 1，右边界是到它为止的所有元素的和。
    因此，如果我们用 pre[i] 表示数组 w 的前缀和：

     */


    class Solution {

        int[] pre;
        int total;

        public Solution(int[] w) {
            pre = new int[w.length];
            pre[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                pre[i] = pre[i - 1] + w[i];
            }
            total = Arrays.stream(w).sum();
        }

        public int pickIndex() {
            int x = (int) (Math.random() * total) + 1;
            return binarySearch(x);
        }

        private int binarySearch(int x) {
            int low = 0, high = pre.length - 1;
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (pre[mid] < x) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }


}
