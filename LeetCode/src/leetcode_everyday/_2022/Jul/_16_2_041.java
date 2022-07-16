package leetcode_everyday._2022.Jul;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author WuChao
 * @create 2022/7/16 9:43
 */
public class _16_2_041 {
    /**
     * 每日一题：2022/7/16
     * <p>
     * 剑指 Offer II 041. 滑动窗口的平均值
     * <p>
     * 难度：easy
     * <p>
     * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。
     * <p>
     * 实现 MovingAverage 类：
     * <p>
     * MovingAverage(int size) 用窗口大小 size 初始化对象。
     * double next(int val) 成员函数 next 每次调用的时候都会往滑动窗口增加一个整数，
     * 请计算并返回数据流中最后 size 个值的移动平均值，即滑动窗口里所有数字的平均值。
     * <p>
     * 示例
     * <p>
     * 输入：
     * inputs = ["MovingAverage", "next", "next", "next", "next"]
     * inputs = [[3], [1], [10], [3], [5]]
     * 输出：
     * [null, 1.0, 5.5, 4.66667, 6.0]
     * <p>
     * 解释：
     * MovingAverage movingAverage = new MovingAverage(3);
     * movingAverage.next(1); // 返回 1.0 = 1 / 1
     * movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
     * movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
     * movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
     * <p>
     * 范围
     * <p>
     * 1 <= size <= 1000
     * -105 <= val <= 105
     * 最多调用 next 方法 10^4 次
     */

    class MovingAverage {

        /**
         * Initialize your data structure here.
         */
        Deque<Integer> integerDeque;
        int size;
        double sum;

        public MovingAverage(int size) {
            integerDeque = new ArrayDeque<>();
            this.size = size;
            this.sum = 0;
        }

        public double next(int val) {
            if (this.integerDeque.size() == this.size) {
                this.sum -= this.integerDeque.peekFirst();
                this.integerDeque.pollFirst();
            }
            this.integerDeque.addLast(val);
            this.sum += val;
            return sum / this.integerDeque.size();

        }
    }
}
