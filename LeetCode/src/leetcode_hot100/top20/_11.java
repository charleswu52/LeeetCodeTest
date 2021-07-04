package leetcode_hot100.top20;

/**
 * @author WuChao
 * @create 2021/7/4 10:55
 */
public class _11 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 11. 盛最多水的容器
     * 难度：medium
     * <p>
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
     * 垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 说明：你不能倾斜容器。
     * <p>
     * 示例
     * <p>
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * <p>
     * 范围
     *
     * n = height.length
     * 2 <= n <= 3 * 104
     * 0 <= height[i] <= 3 * 104
     */

    /*
    思路：双指针法
    首位两个指针向中间移动
     */
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int res = 0;
        while (left < right) {
            int cur = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, cur);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;


    }
}
