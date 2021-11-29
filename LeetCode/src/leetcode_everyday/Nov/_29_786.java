package leetcode_everyday.Nov;

import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2021/11/29 9:01
 */
public class _29_786 {
    /**
     * 每日一题：2021/11/29
     * <p>
     * 519. 随机翻转矩阵
     * <p>
     * 难度：medium
     * <p>
     * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数  组成，且其中所有整数互不相同。
     *
     * 对于每对满足 0 < i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
     *
     * 那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。
     * <p>
     * 输入：arr = [1,2,3,5], k = 3
     * 输出：[2,5]
     * 解释：已构造好的分数,排序后如下所示:
     * 1/5, 1/3, 2/5, 1/2, 3/5, 2/3
     * 很明显第三个最小的分数是 2/5
     *
     *
     * 范围
     * 2 <= arr.length <= 1000
     * 1 <= arr[i] <= 3 * 104
     * arr[0] == 1
     * arr[i] 是一个 素数 ，i > 0
     * arr 中的所有数字 互不相同 ，且按 严格递增 排序
     * 1 <= k <= arr.length * (arr.length - 1) / 2
     *
     */

    /*
    思路：暴力 优先队列
     */
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<int[]> queue  = new PriorityQueue<>((o1, o2) -> {
            double d1 = o1[0] / (double) o1[1];
            double d2 = o2[0] / (double) o2[1];
            return d1 > d2 ? -1 : 1;
        });
        int len = arr.length;

        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                queue.offer(new int[]{arr[j], arr[i]});
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }
        return queue.poll();


    }
}
