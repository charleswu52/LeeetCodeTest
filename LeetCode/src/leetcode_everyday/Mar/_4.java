package leetcode_everyday.Mar;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author WuChao
 * @since 2021/3/4 下午6:57
 */
public class _4 {
    /**
     * 每日一题：2021/3/4
     * 354. 俄罗斯套娃信封问题
     * 难度: hard
     * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
     * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     * 注意：不允许旋转信封。
     *
     * <p>
     * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出：3
     * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     *
     * <p>
     * 数据范围：
     * 1 <= envelopes.length <= 5000
     * envelopes[i].length == 2
     * 1 <= wi, hi <= 104
     */

    /*
    题目解析：找出二维数组的一个排列，使得其中有最长的单调递增子序列（两个维度都递增）。
    在之前的题解里面讲过：「遇事不决先排序」，排序能让数据变成有序的，降低了混乱程度，往往就能帮助我们理清思路。本题也是如此。
    */

    /*
    方法1： 两个维度都递增的排序
   将两个维度都按照递增的排序方法，然后利用最长递增子序列的方法，即使用动态规划的方法。
   定义dp[i]表示以i为结尾的最长递增子序列的长度，对对每个 i 的位置，遍历 [0, i)，对两个维度同时判断是否是严格递增（不可相等）的，
   如果是的话，dp[i] = max(dp[i], dp[j] + 1)。
   时间复杂度：O(n^2)
   空间复杂度：O(n)
   提交LeetCode通过，未提示超时
     */
    public int maxEnvelopes(int[][] envelopes) {
        // 先排序，按照两个维度都递增的顺序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                } else {
                    return a[0] - b[0];
                }
            }
        });
        int len = envelopes.length;
        Integer[] dp = new Integer[len]; //动态规划数组
        Arrays.fill(dp, 1);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return (int) Collections.max(Arrays.asList(dp));   // 返回dp中的最大值
    }

    /*
    方法二：第一维递增，第二维递减的排序
    对上述方法的一个改进技巧：对第一维数据还是递增，第二维改为递减的排序
    先看个例子，假如排序的结果是下面这样：
    [[2, 3], [5, 4], [6, 5], [6, 7]]
    如果我们只看第二个维度 [3,4,5,7]，会得出最长递增子序列的长度是 4 的结论。实际上，由于第 3 和第 4 个信封的第一个维度都是 6，
    导致他们不能套娃。所以，利用第一个维度递增，第二个维度递减的顺序排序，会得到下面的结果：
    [[2, 3], [5, 4], [6, 7], [6, 5]]
    这个时候，只看第二个维度 [3,4,7,5]，就会得到最长递增子序列的长度是 3 的正确结果。
    因此，对应的把上面方法中的排序代码做下修改即可
    时间复杂度与空间复杂度与上一方法相同

     */
    public int maxEnvelopes2(int[][] envelopes) {
        // 先排序，按照两个维度都递增的顺序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return b[1] - a[1]; // 这里按照第二维递减
                } else {
                    return a[0] - b[0];
                }
            }
        });
        // 下面代码与方法1中一样
        int len = envelopes.length;
        Integer[] dp = new Integer[len]; //动态规划数组
        Arrays.fill(dp, 1);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return (int) Collections.max(Arrays.asList(dp));   // 返回dp中的最大值

    }


        public void _21_3_4() {
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
            System.out.println(maxEnvelopes(envelopes));
            System.out.println(maxEnvelopes2(envelopes));
    }
}
