package leetcode_everyday.Apr;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/2 上午8:17
 */
public class _2 {
    /**
     * 每日一题：2021/4/2
     * 面试题 17.21. 直方图的水量
     * 难度: hard
     * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
     * <p>
     * 示例：
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     * <p>
     * 题目链接：
     * https://leetcode-cn.com/problems/volume-of-histogram-lcci/
     * <p>
     * 数据范围：
     */

    /*
    题目解析：
    累计每个直方图柱处能接到的水的总和就是直方图的水量
    对对于下标 i，水能到达的最大高度等于下标 i 两边的最大高度的最小值，下标 i 处能接的水的量等于下标 i 处的水能到达的最大高度减去 height[i]。
    朴素的做法是对于数组 height 中的每个元素，分别向左和向右扫描并记录左边和右边的最大高度，然后计算每个下标位置能接的水的量。
    假设数组 height 的长度为 n，该做法需要对每个下标位置使用 O(n) 的时间向两边扫描并得到最大高度，因此总时间复杂度是 O(n^2)
     */
    /**
     * 优化解法：使用动态规划
     * 如果已经知道每个位置两边的最大高度，则可以在 O(n) 的时间内得到能接的水的总量。
     * 使用动态规划的方法，可以在 O(n) 的时间内预处理得到每个位置两边的最大高度。
     *
     * 创建两个长度为 n 的数组 leftMax 和 rightMax。对于 0 ≤ i < n，leftMax[i] 表示下标 i 及其左边的位置中，height 的最大高度，
     * rightMax[i] 表示下标 i 及其右边的位置中，height 的最大高度。
     *
     * 显然，leftMax[0]=height[0]，rightMax[n−1]=height[n−1]。两个数组的其余元素的计算如下：
     *      当 1≤i≤n−1 时，leftMax[i]=max(leftMax[i−1],height[i])；
     *      当 0≤i≤n−2 时，rightMax[i]=max(rightMax[i+1],height[i])。
     * 因此可以正向遍历数组 height 得到数组 leftMax 的每个元素值，反向遍历数组 height 得到数组 rightMax 的每个元素值。
     *
     * 在得到数组 leftMax 和 rightMax 的每个元素值之后，对于 0 ≤ i < n，下标 i 处能接的水的量等于
     *      min(leftMax[i],rightMax[i])−height[i]。
     *  遍历每个下标位置即可得到能接的水的总量。
     *
     */

    /**
     * 动态规划的实现
     * 属于经典的面试题目，一定掌握！
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // 从左向右遍历，得到每个位置上的leftMax值
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // 从右向左遍历，得到每个位置上的rightMax
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        // 遍历leftMax 与 rightMax，计算差值与height[i]比较，得到当前位置上的容量
        for (int i = 0; i < n; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }


    @Test
    public void test() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));

    }
}
