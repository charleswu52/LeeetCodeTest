package leetcode_hot100.top40;

import sun.text.normalizer.UBiDiProps;

import java.util.Map;

/**
 * @author WuChao
 * @create 2021/7/10 9:07
 */
public class _53 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 53. 最大子序和
     * 难度：easy
     * <p>
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * <p>
     * 范围
     * 1 <= nums.length <= 3 * 104
     * -10^5 <= nums[i] <= 10^5
     *进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
     */

    /*
    思路1： 动态规划
    dp[i] 表示以i结尾的最大子序和
    dp[i]两种情况：
        1.加上前一个位置的最大和: dp[i-1] +nums[i]
        2.不加 它自己是一个新的 nums[i]
        取两者的最大和
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /*
    还是dp的思路，但是不用数组来保存之前的和，只用一个数字记录即可
    空间复杂度为O(1)
     */
    public int maxSubArray2(int[] nums) {
        int res = nums[0];
        int preSum = 0;
        for (int num : nums) {
            preSum = Math.max(preSum, preSum + num);
            res = Math.max(res, preSum);
        }
        return res;
    }

    /*
    思路2： 分治法
    我们定义一个操作 get(a, l, r) 表示查询 aa 序列 [l,r] 区间内的最大子段和，那么最终我们要求的答案就是
    get(nums, 0, nums.size() - 1)。

    如何分治实现这个操作呢？对于一个区间 [l,r]，我们取 m = [ l+r /2 ]，对区间 [l,m] 和 [m+1,r] 分治求解。
    当递归逐层深入直到区间长度缩小为 1 的时候，递归「开始回升」。
    这个时候我们考虑如何通过 [l,m] 区间的信息和 [m+1,r] 区间的信息合并成区间 [l,r] 的信息。最关键的两个问题是：
        我们要维护区间的哪些信息呢？
        我们如何合并这些信息呢？
    对于一个区间 [l,r]，我们可以维护四个量：
        lSum 表示 [l,r] 内以 l 为左端点的最大子段和
        rSum 表示 [l,r] 内以 r 为右端点的最大子段和
        mSum 表示 [l,r] 内的最大子段和
        iSum 表示 [l,r] 的区间和
    以下简称 [l,m] 为 [l,r] 的「左子区间」，[m+1,r] 为 [l,r] 的「右子区间」。
    我们考虑如何维护这些量呢（如何通过左右子区间的信息合并得到 [l,r][l,r] 的信息）？对于长度为 1 的区间 [i,i]，
    四个量的值都和 nums[i] 相等。对于长度大于 1 的区间：
        首先最好维护的是 iSum，区间 [l,r] 的 iSum 就等于「左子区间」的 iSum 加上「右子区间」的 iSum。
        对于 [l,r] 的 lSum，存在两种可能，它要么等于「左子区间」的 lSum，要么等于「左子区间」的 iSum 加上「右子区间」的 lSum，二者取大。
        对于 [l,r] 的 rSum，同理，它要么等于「右子区间」的 rSum，要么等于「右子区间」的 iSum 加上「左子区间」的 rSum，二者取大。
        当计算好上面的三个量之后，就很好计算 [l,r] 的 mSum 了。我们可以考虑 [l,r] 的 mSum 对应的区间是否跨越 m——它可能不跨越 m，
            也就是说 [l,r][l,r] 的 mSum 可能是「左子区间」的 mSum 和 「右子区间」的 mSum 中的一个；它也可能跨越 m，
            可能是「左子区间」的 rSum 和 「右子区间」的 lSum 求和。三者取大。
    这样问题就得到了解决。
     */

    public class Status{
        public int lSum,rSum,mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArray3(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;

    }

    public Status getInfo(int[] nums, int left, int right) {
        if (left == right) {
            return new Status(nums[left], nums[left], nums[left], nums[left]);
        }
        int mid = (left + right) >> 1;

        Status leftSub = getInfo(nums, left, mid);
        Status rightSub = getInfo(nums, mid + 1, right);
        return pushUp(leftSub, rightSub);

    }

    public Status pushUp(Status left, Status right) {
        int iSum = left.iSum + right.iSum;
        int lSum = Math.max(left.lSum, left.iSum + right.lSum);
        int rSum = Math.max(right.rSum, right.iSum + left.rSum);
        int mSum = Math.max(Math.max(left.mSum, right.mSum), left.rSum + right.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}
