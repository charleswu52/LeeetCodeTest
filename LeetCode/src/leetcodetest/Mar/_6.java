package leetcodetest.Mar;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author WuChao
 * @since 2021/3/6 上午8:15
 */
public class _6 {
    /**
     * 每日一题：2021/3/6
     * 503. 下一个更大元素 II
     * 难度: medium
     * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
     * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
     * 如果不存在，则输出 -1。
     *
     * <p>
     * 示例:
     * 输入: [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     *
     * <p>
     * 数据范围：
     * 输入数组的长度不会超过 10000。
     */

    /*
    思路1:直接按照题目介绍的寻找下一个比当前元素大的值去遍历查找
    时间复杂度：O(n^2)
     */
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        for (int i = 0; i < len; i++) {
//            boolean found = false;
            for (int j = i + 1; j < i + len; j++) {
                if (nums[j % len] > nums[i]) {
                    res[i] = nums[j % len];
//                    found = true;
                    break;
                }
            }
//            if (!found) {
//                res[i] = -1;
//            }
        }

        return res;

    }

    /*
    思路2：单调栈（参考其他题解）
    关于单调栈的使用说明，看_6_2的扩展题目
    这里与扩展题目不同之处就是数组改为可以循环移动的
     */
    public int[] nextGreaterElements2(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        Arrays.fill(ans, -1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < len * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[i % len] > nums[stack.peek()]) {
                ans[stack.pop()] = nums[i % len];
            }
            stack.push(i % len);
        }
        return ans;
    }


    public void _21_3_6() {
        int[] nums = {1, 2, 1};
        System.out.println(Arrays.toString(nextGreaterElements(nums)));
        System.out.println(Arrays.toString(nextGreaterElements2(nums)));
    }
}
