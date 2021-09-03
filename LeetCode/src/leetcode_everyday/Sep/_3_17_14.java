package leetcode_everyday.Sep;

import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2021/9/3 14:03
 */
public class _3_17_14 {
    /**
     * 每日一题：2021/9/3
     * 面试题 17.14. 最小K个数
     * 难度：medium
     * <p>
     * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     * <p>
     * 示例：
     * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
     * 输出： [1,2,3,4]
     *
     * <p>
     * 注意:
     * 0 <= len(arr) <= 100000
     * 0 <= k <= min(100000, len(arr))
     */

    /*
    思路：大顶堆
    用一个大根堆实时维护数组的前 k 小值。首先将前k个数插入大根堆中，随后从第k+1个数开始遍历，如果当前遍历到的数比大根堆的堆顶的数要小，
    就把堆顶的数弹出，再插入当前遍历到的数。最后将大根堆中的数存入数组返回即可。
     */
    public int[] smallestK(int[] arr, int k) {
        int[] res = new int[k];
        if (k == 0) {
            return res;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int n = arr.length;
        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < n; i++) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;


    }
}
