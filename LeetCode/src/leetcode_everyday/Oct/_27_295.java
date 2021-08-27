package leetcode_everyday.Oct;

import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2021/8/27 8:52
 */
public class _27_295 {
    /**
     * 每日一题：2021/8/27
     * 295. 数据流的中位数
     * 难度：hard
     * <p>
     * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
     * 例如，
     * [2,3,4] 的中位数是 3
     * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
     * 设计一个支持以下两种操作的数据结构：
     * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
     * double findMedian() - 返回目前所有元素的中位数。
     * <p>
     * 示例：
     * addNum(1)
     * addNum(2)
     * findMedian() -> 1.5
     * addNum(3)
     * findMedian() -> 2
     *
     * <p>
     * 进阶:
     * <p>
     * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
     * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
     */

    /*
    思路：大小顶堆
    两个优先队列 queMax 和 queMin 分别记录大于中位数的数和小于等于中位数的数。

    当累计添加的数的数量为奇数时，queMin 中的数的数量比 queMax 多一个，此时中位数为 queMin 的队头。
    当累计添加的数的数量为偶数时，两个优先队列中的数的数量相同，此时中位数为它们的队头的平均值。
     */

    class MedianFinder {

        /**
         * initialize your data structure here.
         */

        PriorityQueue<Integer> queMin;
        PriorityQueue<Integer> queMax;

        public MedianFinder() {
            queMax = new PriorityQueue<>((o1, o2) -> o1 - o2);
            queMin = new PriorityQueue<>((o1, o2) -> o2 - o1);

        }

        public void addNum(int num) {
            if (queMin.isEmpty() || num <= queMin.peek()) {
                queMin.offer(num);
                if (queMax.size() + 1 < queMin.size()) {
                    queMax.offer(queMin.poll());
                }
            } else {
                queMax.offer(num);
                if (queMax.size() > queMin.size()) {
                    queMin.offer(queMax.poll());
                }
            }

        }

        public double findMedian() {
            if (queMin.size() > queMax.size()) {
                return queMin.peek();
            }
            return (queMin.peek() + queMax.peek()) / 2.0;

        }
    }
}
