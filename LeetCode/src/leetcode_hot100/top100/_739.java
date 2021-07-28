package leetcode_hot100.top100;

import java.util.Stack;

/**
 * @author WuChao
 * @create 2021/7/28 19:04
 */
public class _739 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 739. 每日温度
     * 难度：medium
     * <p>
     * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 示例 1:
     *
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     * 示例 2:
     *
     * 输入: temperatures = [30,40,50,60]
     * 输出: [1,1,1,0]
     * 示例 3:
     *
     * 输入: temperatures = [30,60,90]
     * 输出: [1,1,0]
     *
     *
     * 提示：
     *
     * 1 <= temperatures.length <= 105
     * 30 <= temperatures[i] <= 100
     */

    /*
    题目解析：
    实际上是求每个数右边第一个比它大的数跟它之间的距离

    思路1：暴力法
    对每个值 向后搜索 找到第一个比他大的数，计算下标之间的距离
    时间复杂度 O(n^2)


    思路2：单调栈
    具体操作如下：
    使用单调递减栈
    遍历整个数组，如果栈不空，且当前数字大于栈顶元素，那么如果直接入栈的话就不是 递减栈 ，所以需要取出栈顶元素，由于当前数字大于栈顶元素的数字，
    而且一定是第一个大于栈顶元素的数，直接求出下标差就是二者的距离。

    继续看新的栈顶元素，直到当前数字小于等于栈顶元素停止，然后将数字入栈，这样就可以一直保持递减栈，且每个数字和第一个大于它的数的距离也可以算出来。

    时间复杂度：O(n)
    空间复杂度：O(n)
     */

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;

    }

}
