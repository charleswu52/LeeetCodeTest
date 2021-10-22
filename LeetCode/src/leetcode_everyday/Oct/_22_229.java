package leetcode_everyday.Oct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/10/22 8:27
 */
public class _22_229 {
    /**
     * 每日一题：2021/10/22
     * <p>
     * 229. 求众数 II
     * <p>
     * 难度：medium
     * <p>
     * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     * <p>
     * 示例1：
     * <p>
     * 输入：[3,2,3]
     * <p>
     * 输出：[3]
     * <p>
     * 示例2：
     * <p>
     * 输入：[1,1,1,3,3,2,2,2]
     * <p>
     * 输出：[1,2]
     * <p>
     * 1 <= nums.length <= 5 * 104
     * <p>
     * -10^9 <= nums[i] <= 10^9
     *
     * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题
     */

    /*
    思路分析：
    最简单的思路就是使用哈希表计数，这种做法的时间复杂度和空间复杂度均为O(n)

    进阶做法：摩尔投票法（进阶）
    之前用摩尔投票法做过求一个数组中出现次数超过两次的问题
     */

    public List<Integer> majorityElement(int[] nums) {

        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int threshold = nums.length / 3;

        // 初始化两个候选人candidate和他们的计票
        int cand1 = nums[0], count1 = 0;
        int cand2 = nums[0], count2 = 0;
        // 开始摩尔投票计数，分为两个阶段：配对阶段和计数阶段
        // 配对阶段
        for (int num : nums) {
            // 投票
            if (cand1 == num) {
                count1++;
                continue;
            }
            if (cand2 == num) {
                count2++;
                continue;
            }

            // 第1个候选人配对
            if (count1 == 0) {
                cand1 = num;
                count1++;
                continue;
            }
            // 第2个候选人配对
            if (count2 == 0) {
                cand2 = num;
                count2++;
                continue;
            }
            count1--;
            count2--;
        }

        // 计数阶段
        // 找到了两个候选人之后，需要确定票数是否满足大于 N/3
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (cand1 == num) {
                count1++;
            } else if (cand2 == num) {
                count2++;
            }
        }
        if (count1 > threshold) {
            res.add(cand1);
        }
        if (count2 > threshold) {
            res.add(cand2);
        }

        return res;

    }
}
