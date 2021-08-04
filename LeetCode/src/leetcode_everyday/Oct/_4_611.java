package leetcode_everyday.Oct;

import java.util.Arrays;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/8/4 21:09
 */
public class _4_611 {
    /**
     * 每日一题：2021/8/4
     * 611. 有效三角形的个数
     * 难度：medium
     * <p>
     * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
     *
     * <p>
     * 示例 1:
     * 输入: [2,2,3,4]
     * 输出: 3
     * 解释:
     * 有效的组合是:
     * 2,3,4 (使用第一个 2)
     * 2,3,4 (使用第二个 2)
     * 2,2,3
     *
     * <p>
     * 注意:
     * 数组长度不超过1000。
     * 数组里整数的范围为 [0, 1000]。
     */

    /*
    思路：排序 + 双指针

    三角形的构成要点是两边之和大于第三边，所以只要将原数组从小到大排序，因为所有测试数据均为正值
    因此 对于 a <= b <= c 来说，如果a+b>c 成立的话，那一定可以组成三角形

    我们将当 a=nums[i],b=nums[j] 时，最大的满足 nums[k]<nums[i]+nums[j] 的下标 k 记为 k_{i, j} 。
    可以发现，如果我们固定 i，那么随着 j 的递增，不等式右侧 nums[i]+nums[j] 也是递增的，因此 k_{i, j} 也是递增的。
    这样一来，我们就可以将 j 和 k 看成两个同向（递增）移动的指针：
        我们使用一重循环枚举 i。当 i 固定时，我们使用双指针同时维护 j 和 k，它们的初始值均为 i；
        我们每一次将 j 向右移动一个位置，即 j←j+1，并尝试不断向右移动 k，使得 k 是最大的满足 nums[k]<nums[i]+nums[j] 的下标。
        我们将 max(k−j,0) 累加入答案。
    当枚举完成后，我们返回累加的答案即可。

     */
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        int res = 0;
        if (n < 3) {
            return res;
        }
        // 先排序
        Arrays.sort(nums);

        // 外层循环确定一个i
        for (int i = 0; i < n; i++) {
            int k = i;
            for (int j = i + 1; j < n; j++) {
                while (k + 1 < n && nums[k + 1] < nums[i] + nums[j]) {
                    k++;
                }
                res += Math.max(0, k - j);
            }
        }
        return res;


    }
}
