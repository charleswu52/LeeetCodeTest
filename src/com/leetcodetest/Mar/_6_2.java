package com.leetcodetest.Mar;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author WuChao
 * @since 2021/3/6 上午8:51
 */
public class _6_2 {
    /**
     * 每日一题：2021/3/6  相关的题目
     * 739. 每日温度
     * 难度: medium
     * 根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
     * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * <p>
     * 示例：
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
     *                   你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * <p>
     * 数据范围：
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     */

    /*
    思路：单调栈解法

    单调栈的适用场景：
    通常是一维数组，要寻找任一个元素的右边或者左边第一个比自己大或者小的元素的位置，此时我们就要想到可以用单调栈了。

    单调栈的有时间复杂度是O(n)
    单调栈的本质是空间换时间，因为在遍历的过程中需要用一个栈来记录右边第一个比当前元素的X元素，优点是只需要遍历一次。

    在使用单调栈的时候首先要明确如下几点：
    1.单调栈里存放的元素是什么？
    单调栈里只需要存放元素的下标i就可以了，如果需要使用对应的元素，直接T[i]就可以获取。
    2.单调栈里元素是递增呢？ 还是递减呢？
    注意一下顺序为 从栈头到栈底的顺序，因为单纯的说从左到右或者从前到后，不说栈头朝哪个方向的话，大家一定会越看越懵。
    这里我们要使用递增循序（再强调一下是指 从栈头到栈底 的顺序），
    因为只有递增的时候，加入一个元素i，才知道栈顶元素在数组中右面第一个比栈顶元素大的元素是i。

    正向遍历温度列表。对于温度列表中的每个元素 T[i]，如果栈为空，则直接将 i 进栈，
    如果栈不为空，则比较栈顶元素 prevIndex 对应的温度 T[prevIndex] 和当前温度 T[i]，如果 T[i] > T[prevIndex]，
        则将 prevIndex 移除，并将 prevIndex 对应的等待天数赋为 i - prevIndex，
        重复上述操作直到栈为空或者栈顶元素对应的温度小于等于当前温度，然后将 i 进栈。
    为什么可以在弹栈的时候更新 ans[prevIndex] 呢？因为在这种情况下，即将进栈的 i 对应的 T[i] 一定是 T[prevIndex] 右边第一个比它大的元素，
    试想如果 prevIndex 和 i 有比它大的元素，假设下标为 j，那么 prevIndex 一定会在下标 j 的那一轮被弹掉。
    由于单调栈满足从栈底到栈顶元素对应的温度递减，因此每次有元素进栈时，会将温度更低的元素全部移除，并更新出栈元素对应的等待天数，
    这样可以确保等待天数一定是最小的。
     */
    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        int[] ans = new int[len];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            int temperature = T[i];
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }
    public void _21_3_6_2() {
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(T)));
    }
}
