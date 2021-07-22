package leetcode_hot100.top80;

/**
 * @author WuChao
 * @create 2021/7/22 上午9:04
 */
public class _300 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 300. 最长递增子序列
     * 难度：medium
     * <p>
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * <p>
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * <p>
     * 示例 1：
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * <p>
     * 示例 2：
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     * <p>
     * 提示：
     * 1 <= nums.length <= 2500
     * -104 <= nums[i] <= 104
     * <p>
     * 进阶：
     * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
     * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
     */

    /*
    思路：动态规划
    定义dp数组 dp[i] 表示以i为结尾的最长上升子序列的长度 其中 nums[i] 必须被选取
    dp[i] = max(dp[j]) + 1 (0 <=j < i) 而且 nums[i]必须 大于 nums[j]
    因此需要遍历之前的状态看是否能转移过去
    时间复杂度是 O(n^2)
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 状态转移方程
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /*
    思路2：二分查找
    维护一个数组 d[i] ，表示长度为 i 的最长上升子序列的末尾元素的最小值，用 len 记录目前最长上升子序列的长度，起始时 len 为 1，
    d[1] = nums[0]。

    依次遍历数组 nums 中的每个元素，并更新数组 d 和 len 的值。如果 nums[i]>d[len] 则更新 len = len + 1，否则在 d[1…len]
    中找满足 d[i−1]<nums[j]<d[i] 的下标 i，并更新 d[i]=nums[j]。

    根据 d 数组的单调性，我们可以使用二分查找寻找下标 i，优化时间复杂度。

    最后整个算法流程为：
    设当前已求出的最长上升子序列的长度为 len（初始时为 1），从前往后遍历数组 nums，在遍历到 nums[i] 时：

    如果 nums[i]>d[len] ，则直接加入到 d 数组末尾，并更新 len=len+1；

    否则，在 d 数组中二分查找，找到第一个比 nums[i] 小的数 d[k] ，并更新 d[k+1]=nums[i]。


    时间复杂度 O(nlogn)
     */

    public int lengthOfLIS2(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 1) {
            return n;
        }
        int[] d = new int[n + 1];
        d[1] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int left = 1, right = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

}
