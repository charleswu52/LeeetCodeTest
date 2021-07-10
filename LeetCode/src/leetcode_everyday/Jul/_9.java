package leetcode_everyday.Jul;

/**
 * @author WuChao
 * @create 2021/7/9 下午3:20
 */
public class _9 {
    /**
     * 每日一题：2021/7/9
     * 面试题 17.10. 主要元素
     * 难度: medium
     * <p>
     * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。
     * 请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
     * <p>
     * 示例
     * 输入：[1,2,5,9,5,9,5,5,5]
     * 输出：5
     * <p>
     * 输入：[3,2]
     * 输出：-1
     * <p>
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     * <p>
     * 限制：
     */

    /*
    思路：摩尔根投票算法
     */
    public int majorityElement(int[] nums) {
        int len = nums.length;
        int maxCount = 0; // 出现次数最多的 但不是总次数
        int num = -1;
        for (int i = 0; i < len; i++) {
            if (maxCount == 0) {
                num = nums[i];
                maxCount++;
            } else if (nums[i] == num) {
                maxCount++;
            } else {
                maxCount--;
            }
        }
        /*
        再遍历一遍 对出现次数最多的数统计它出现的真正次数
         */
        int n = 0;
        if (maxCount > 0) {
            for (int t : nums) {
                if (t == num) {
                    n++;
                }
            }
        }
        return n > len / 2 ? num : -1;

    }


}
