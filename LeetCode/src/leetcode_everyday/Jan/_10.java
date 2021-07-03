package leetcode_everyday.Jan;

import java.util.ArrayList;
import java.util.List;

public class _10 {
    /**
     * 每日一题：2021/1/10
     * 给定一个无重复元素的有序整数数组 nums 。
     * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。
     * 也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
     * <p>
     * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
     * "a->b" ，如果 a != b
     * "a" ，如果 a == b
     * <p>
     * 示例：
     * 输入：nums = [0,1,2,4,5,7]
     * 输出：["0->2","4->5","7"]
     * 解释：区间范围是：
     * [0,2] --> "0->2"
     * [4,5] --> "4->5"
     * [7,7] --> "7"
     */


    /**
     * 思路：一次遍历
     * 从数组的位置 00 出发，向右遍历。每次遇到相邻元素之间的差值大于 11 时，我们就找到了一个区间。遍历完数组之后，就能得到一系列的区间的列表。
     * 在遍历过程中，维护下标 low 和 high 分别记录区间的起点和终点，对于任何区间都有 low≤high。
     * 当得到一个区间时，根据 low 和 high 的值生成区间的字符串表示。
     *
     * 当 low<high 时，区间的字符串表示为 “low→high"；
     * 当 low=high 时，区间的字符串表示为 ”low"
     * @param nums
     * @return
     */
    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<String>();
        int n = nums.length;
        int i = 0;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            if (low < high) {
                res.add(nums[low] + "->" + nums[high]);
            } else {
                res.add(nums[low]+"");
            }
        }
        return res;
    }

    public static void _21_1_10() {
        int[] nums = {0, 1, 2, 4, 5, 7};
        List<String> res = summaryRanges(nums);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

    }
}
