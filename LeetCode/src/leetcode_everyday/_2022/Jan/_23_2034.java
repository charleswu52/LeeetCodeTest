package leetcode_everyday._2022.Jan;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2022/1/23 10:29
 */
public class _23_2034 {
    /**
     * 每日一题：2022/1/23
     * <p>
     * 2034. 股票价格波动
     * <p>
     * 难度：medium
     * <p>
     * 给你一支股票价格的数据流。数据流中每一条记录包含一个 时间戳 和该时间点股票对应的 价格 。
     * <p>
     * 不巧的是，由于股票市场内在的波动性，股票价格记录可能不是按时间顺序到来的。某些情况下，有的记录可能是错的。
     * 如果两个有相同时间戳的记录出现在数据流中，前一条记录视为错误记录，后出现的记录 更正 前一条错误的记录。
     * <p>
     * 请你设计一个算法，实现：
     * <p>
     * 更新 股票在某一时间戳的股票价格，如果有之前同一时间戳的价格，这一操作将 更正 之前的错误价格。
     * 找到当前记录里 最新股票价格 。最新股票价格 定义为时间戳最晚的股票价格。
     * 找到当前记录里股票的 最高价格 。
     * 找到当前记录里股票的 最低价格 。
     * 请你实现 StockPrice 类：
     * <p>
     * StockPrice() 初始化对象，当前无股票价格记录。
     * void update(int timestamp, int price) 在时间点 timestamp 更新股票价格为 price 。
     * int current() 返回股票 最新价格 。
     * int maximum() 返回股票 最高价格 。
     * int minimum() 返回股票 最低价格 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * <p>
     * ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
     * [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
     * <p>
     * 输出：
     * <p>
     * [null, null, null, 5, 10, null, 5, null, 2]
     * <p>
     * 解释：
     * <p>
     * StockPrice stockPrice = new StockPrice();
     * stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
     * stockPrice.update(2, 5);  // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
     * stockPrice.current();     // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
     * stockPrice.maximum();     // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
     * stockPrice.update(1, 3);  // 之前时间戳为 1 的价格错误，价格更新为 3 。
     * // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
     * stockPrice.maximum();     // 返回 5 ，更正后最高价格为 5 。
     * stockPrice.update(4, 2);  // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
     * stockPrice.minimum();     // 返回 2 ，最低价格时间戳为 4 ，价格为 2 。
     * <p>
     * 范围
     * <p>
     * 1 <= timestamp, price <= 10^9
     * update，current，maximum 和 minimum 总 调用次数不超过 10^5 。
     * current，maximum 和 minimum 被调用时，update 操作 至少 已经被调用过 一次 。
     */

    /*
    思路：哈希表 优先队列
     */
    class StockPrice {

        private HashMap<Integer, Integer> stock;
        private PriorityQueue<int[]> minQueue;
        private PriorityQueue<int[]> maxQueue;
        private int lastTime;

        public StockPrice() {
            this.stock = new HashMap<>();
            this.lastTime = -1;
            this.minQueue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            this.maxQueue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        }

        public void update(int timestamp, int price) {
            stock.put(timestamp, price);
            if (lastTime == -1 || timestamp > lastTime) {
                lastTime = timestamp;
            }
            if (minQueue.isEmpty()) {
                minQueue.offer(new int[]{timestamp, price});
            } else {
                int[] curMin = minQueue.peek();
                if (curMin[0] == timestamp) {
                    minQueue.poll();
                }
                minQueue.offer(new int[]{timestamp, price});
            }
            if (maxQueue.isEmpty()) {
                maxQueue.offer(new int[]{timestamp, price});
            } else {
                int[] curMax = maxQueue.peek();
                if (curMax[0] == timestamp) {
                    maxQueue.poll();
                }
                maxQueue.offer(new int[]{timestamp, price});
            }
        }

        public int current() {
            return stock.getOrDefault(lastTime,0);
        }

        public int maximum() {
            while (!maxQueue.isEmpty()) {
                int[] peek = maxQueue.peek();
                if (peek[1] == stock.get(peek[0])) {
                    return peek[1];
                } else {
                    maxQueue.poll();
                }
            }
            return -1;
        }

        public int minimum() {
            while (!minQueue.isEmpty()) {
                int[] peek = minQueue.peek();
                if (peek[1] == stock.get(peek[0])) {
                    return peek[1];
                } else {
                    minQueue.poll();
                }
            }
            return -1;
        }
    }
}
