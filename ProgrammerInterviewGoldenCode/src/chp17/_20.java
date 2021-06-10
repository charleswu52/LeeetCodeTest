package chp17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2021/6/10 上午10:15
 */
public class _20 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.20. 连续中值
     * 难度: hard
     * <p>
     * 随机产生数字并传递给一个方法。你能否完成这个方法，在每次产生新值时，寻找当前所有值的中间值（中位数）并保存。
     * <p>
     * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
     * <p>
     * 例如，
     * <p>
     * [2,3,4] 的中位数是 3
     * <p>
     * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
     * <p>
     * 设计一个支持以下两种操作的数据结构：
     * <p>
     * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
     * double findMedian() - 返回目前所有元素的中位数。
     *
     *
     * <p>
     * 示例:
     * addNum(1)
     * addNum(2)
     * findMedian() -> 1.5
     * addNum(3)
     * findMedian() -> 2
     * <p>
     * 数据范围：
     */

    /*
    思路1：直接排序
     */
    class MedianFinder {

        /**
         * initialize your data structure here.
         */
        private int size;
        private List<Integer> store;

        public MedianFinder() {
            this.size = 0;
            this.store = new ArrayList<>();
        }

        public void addNum(int num) {
            this.size++;
            store.add(num);
            Collections.sort(store);
        }

        public double findMedian() {
            int a = 0, b = 0;
            if (size / 2 <= size) {
                a = store.get(size / 2);
            }
            if (size / 2 - 1 >= 0) {
                b = store.get(size / 2 - 1);
            }
            if (size % 2 == 1) {
                return a;
            } else {
                return (a + b) / 2.0;
            }

        }
    }

    /*
    思路2： 大小堆
    利用一个大根堆和小根堆来实现，中位数用来分割两个堆，
    如果列表长度为奇数，中位数就是大顶堆的堆顶，为偶数的话，就为大根堆和小根堆堆顶的平均值
     */

    class MedianFinder2 {

        private PriorityQueue<Integer> small;
        private PriorityQueue<Integer> big;

        public MedianFinder2() {
            this.small = new PriorityQueue<>();
            this.big = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        }

        public void addNum(int num) {
            big.offer(num);
            small.offer(big.poll());
            if (big.size() < small.size()) { // 奇数的情况
                big.offer(small.poll());
            }
        }

        public double findMedian() {
            if (big.size() > small.size()) {
                return big.peek();
            } else {
                return (big.peek() + small.peek()) / 2.0;
            }

        }

    }
}
