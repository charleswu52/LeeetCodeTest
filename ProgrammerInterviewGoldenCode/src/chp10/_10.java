package chp10;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author WuChao
 * @since 2021/5/26 下午12:47
 */
public class _10 {
    /**
     * 程序员面试金典(version 6) - 面试题 10.10. 数字流的秩
     * 难度: medium
     * <p>
     * 设你正在读取一串整数。每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。请实现数据结构和算法来支持这些操作，也就是说：
     * <p>
     * 实现 track(int x) 方法，每读入一个数字都会调用该方法；
     * <p>
     * 实现 getRankOfNumber(int x) 方法，返回小于或等于 x 的值的个数。
     * <p>
     * 注意：本题相对原题稍作改动
     * <p>
     * 示例:
     * 输入:
     * ["StreamRank", "getRankOfNumber", "track", "getRankOfNumber"]
     * [[], [1], [0], [0]]
     * 输出:
     * [null,0,null,1]
     * <p>
     * 数据范围：
     * x <= 50000
     * track 和 getRankOfNumber 方法的调用次数均不超过 2000 次
     */

    class StreamRank {

        private TreeMap<Integer, Integer> store;

        public StreamRank() {
            this.store = new TreeMap<>();
        }

        public void track(int x) {
            if (store.containsKey(x)) {
                store.put(x, store.getOrDefault(x, 0) + 1);
            } else {
                store.put(x, 1);
            }

        }

        public int getRankOfNumber(int x) {
            int res = 0;
            if (store.isEmpty()) {
                return res;
            }
            Iterator<Map.Entry<Integer, Integer>> iterator = store.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> integerIntegerEntry = iterator.next();
                if (integerIntegerEntry.getKey() <= x) {
                    res += integerIntegerEntry.getValue();
                } else {
                    return res;
                }
            }
            return res;
        }
    }
}
