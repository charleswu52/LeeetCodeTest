package leetcode_hot100.top20;

/**
 * @author WuChao
 * @create 2021/7/6 13:57
 */
public class _42 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 42. 接雨水
     * 难度：hard
     * <p>
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * <p>
     * 示例
     * <p>
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     *
     *
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     * <p>
     * 范围
     * n == height.length
     * 0 <= n <= 3 * 104
     * 0 <= height[i] <= 105
     */

    /*
    思路：动态规划
    经典面试题，使用动态规划来做，牢记！

    需要两次扫描找到每个下标点处左边的最大高度和右边的最大高度

    创建两个长度为 n 的数组 leftMax 和 rightMax。对于 0≤i<n，leftMax[i] 表示下标 i 及其左边的位置中，height 的最大高度，
    rightMax[i] 表示下标 i 及其右边的位置中，\}height 的最大高度。

    显然，leftMax[0]=height[0]，rightMax[n−1]=height[n−1]。两个数组的其余元素的计算如下：

    当  1≤i≤n−1 时，leftMax[i]=max(leftMax[i−1],height[i])；
    当  0≤i≤n−2 时，rightMax[i]=max(rightMax[i+1],height[i])。

    因此可以正向遍历数组 height 得到数组 leftMax 的每个元素值，反向遍历数组 height 得到数组 rightMax 的每个元素值。

    在得到数组 leftMax 和 rightMax 的每个元素值之后，对于 0≤i<n，下标 i 处能接的雨水量等于 min(leftMax[i],rightMax[i])−height[i]。
    遍历每个下标位置即可得到能接的雨水总量。

     */
    public int trap(int[] height) {
        if (height == null || height.length==0) {
            return 0;
        }
        int len = height.length;

        int[] leftMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[len];
        rightMax[len - 1] = height[len - 1];
        for (int i = len-2; i >=0 ; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;

    }
}
