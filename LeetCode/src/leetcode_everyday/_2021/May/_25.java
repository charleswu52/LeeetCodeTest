package leetcode_everyday._2021.May;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @since 2021/5/25 上午7:50
 */
public class _25 {
    /**
     * 每日一题：2021/5/25
     * 1787. 使所有区间的异或结果为零
     * 难度: hard
     * <p>
     * 给你一个整数数组 nums 和一个整数 k 。区间 [left, right]（left <= right）的 异或结果 是对下标位于 left 和 right
     * （包括 left 和 right ）之间所有元素进行 XOR 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。
     * 返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
     *
     * <p>
     * 示例：
     * 输入：nums = [1,2,0,3,0], k = 1
     * 输出：3
     * 解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
     * <p>
     * 输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
     * 输出：3
     * 解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为 [3,4,7,3,4,7,3,4,7]
     * <p>
     * 数据范围：
     * 1 <= k <= nums.length <= 2000
     * 0 <= nums[i] < 210
     */

    /*
    题目解析：
    要求是对给定的数组nums,修改数组的值，使得每一个长度为k的滑动窗口内所有元素的异或值为0
    求修改元素的最小数
     */

    /*
    题目分析：
    将数组中所有元素，分成n/k行，k列，修改后每一列的元素都是相同的
    ------
    a b c
    d e f
    ------
    a ^ b ^ c = 0
    b ^ c ^ d = 0  ==> a = d ,即每一列的元素都是相同的
    ---------------------
    要求修改元素的最小值，看到类似求最小方案数这种的问题，思路就是使用动态规划。
    那怎么定义动态规划数组，递推方程是什么，初始条件，边界条件又是什么，按照什么顺序来求dp数组，这些就是题目的难点
     */
    /*
    参考题解：
    定义dp数组，dp[i][j]：表示每个窗口中前i个数字进行异或，得到j所需的最少次数(0<=i<=k-1)

    dp[0]的初始化：此时异或为 j 需要操作的最小次数，即为 第0列元素总数 - 第0列中j的总数
    dp[i]的初始化：对于某一列数字，最多操作 group_element 次（此时相当于改变本列所有元素；数量上有可能为 n/k 或者 n/k−1）。
    要使这种情况最小，应该取 dp[i-1] 中的最小值(记为 min_prv)，使得 dp[i][j]=min_prv+group_amount

    转移方程：
    在初始化中，我似乎已经提到了一些关于转移方程的事情。鉴于我们已经将所有元素都取到了其最小的最大值，
    因此此时我们只需要考虑可以减少操作次数的转移（即使其不为最大值的情况）。
    dp[i][j] = min_{num}{dp[i-1][j^num] + group_amount - ele_amount[ele]}}
    上式的 num，应该在此列中出现至少一次。
     */

    // x 的范围为 [0, 2^10)
    static final int MAXX = 1 << 10;
    // 极大值，为了防止整数溢出选择 INT_MAX / 2
    static final int INFTY = Integer.MAX_VALUE / 2;

    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[MAXX];
        Arrays.fill(f, INFTY);
        // 边界条件 f(-1,0)=0
        f[0] = 0;

        for (int i = 0; i < k; ++i) {
            // 第 i 个组的哈希映射
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            int size = 0;
            for (int j = i; j < n; j += k) {
                cnt.put(nums[j], cnt.getOrDefault(nums[j], 0) + 1);
                ++size;
            }

            // 求出 t2
            int t2min = Arrays.stream(f).min().getAsInt();

            int[] g = new int[MAXX];
            Arrays.fill(g, t2min);
            for (int mask = 0; mask < MAXX; ++mask) {
                // t1 则需要枚举 x 才能求出
                for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                    int x = entry.getKey(), countx = entry.getValue();
                    g[mask] = Math.min(g[mask], f[mask ^ x] - countx);
                }
            }

            // 别忘了加上 size
            for (int j = 0; j < MAXX; ++j) {
                g[j] += size;
            }
            f = g;
        }

        return f[0];
    }

}
