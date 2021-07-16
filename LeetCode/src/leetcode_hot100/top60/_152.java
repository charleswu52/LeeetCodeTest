package leetcode_hot100.top60;

/**
 * @author WuChao
 * @create 2021/7/16 12:56
 */
public class _152 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 152. 乘积最大子数组
     * 难度：medium
     * <p>
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * <p>
     * 进阶：
     * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
     *
     *
     * <p>
     * 输入：head = [4,2,1,3]
     * 输出：[1,2,3,4]
     * <p>
     * 输入：head = [-1,5,3,4,0]
     * 输出：[-1,0,3,4,5]
     *
     *
     * <p>
     * 数据范围:
     */

    /*
    思路：动态规划
    但是要考虑的一个问题就是正负的问题

    根据正负性进行分类讨论。

    考虑当前位置如果是一个负数的话，那么我们希望以它前一个位置结尾的某个段的积也是个负数，这样就可以负负得正，
        并且我们希望这个积尽可能「负得更多」，即尽可能小。
    如果当前位置是一个正数的话，我们更希望以它前一个位置结尾的某个段的积也是个正数，并且希望它尽可能地大。

    于是这里我们可以再维护一个 f_min (i)，它表示以第 i 个元素结尾的乘积最小子数组的乘积，那么我们可以得到这样的动态规划转移方程：
     */

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int maxF = nums[0];
        int minF = nums[0];
        int res = nums[0];

        for (int i = 1; i < n; i++) {
            int mx = maxF, mn = minF;
            maxF = Math.max(nums[i] * mx, Math.max(nums[i], mn * nums[i]));
            minF = Math.min(nums[i] * mn, Math.min(nums[i], mx * nums[i]));
            res = Math.max(maxF, res);
        }
        return res;
    }
}
