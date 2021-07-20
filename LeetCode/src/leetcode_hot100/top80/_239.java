package leetcode_hot100.top80;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2021/7/20 13:52
 */
public class _239 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 239. 滑动窗口最大值
     * 难度：hard
     * <p>
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
     * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * <p>
     * 示例 1：
     *
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     *
     * 输入：nums = [1], k = 1
     * 输出：[1]
     *
     * 输入：nums = [9,11], k = 2
     * 输出：[11]
     *
     * <p>
     *
     *
     */

    /*
    思路：
    优先队列 + 滑动窗口
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 定义优先队列保存窗口中元素的值以及对应的下标
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });
        int[] res = new int[n - k + 1];
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(new int[]{nums[i], i});
        }

        for (int i = k; i < n; i++) {
            priorityQueue.offer(new int[]{nums[i], i});
            while (priorityQueue.peek()[1] <= i - k) {
                priorityQueue.poll();
            }
            res[i - k + 1] = priorityQueue.peek()[0];

        }
        return res;

    }
}
