package leetcode_everyday._2021.Jul;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/10 7:49
 */
public class _10 {
    /**
     * 每日一题：2021/7/10
     * 981. 基于时间的键值存储
     * 难度: medium
     * <p>
     * 创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作：
     *
     * 1. set(string key, string value, int timestamp)
     *      存储键 key、值 value，以及给定的时间戳 timestamp。
     * 2. get(string key, int timestamp)
     *      返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。
     *      如果有多个这样的值，则返回对应最大的  timestamp_prev 的那个值。
     *      如果没有值，则返回空字符串（""）。
     * <p>
     * 示例
     * 输入：inputs = ["TimeMap","set","get","get","set","get","get"],
     *      inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
     * 输出：[null,null,"bar","bar",null,"bar2","bar2"]
     * 解释：
     * TimeMap kv;
     * kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1
     * kv.get("foo", 1);  // 输出 "bar"
     * kv.get("foo", 3); // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"）
     * kv.set("foo", "bar2", 4);
     * kv.get("foo", 4); // 输出 "bar2"
     * kv.get("foo", 5); // 输出 "bar2"
     * <p>
     *
     * <p>
     * 限制：
     * 所有的键/值字符串都是小写的。
     * 所有的键/值字符串长度都在 [1, 100] 范围内。
     * 所有 TimeMap.set 操作中的时间戳 timestamps 都是严格递增的。
     * 1 <= timestamp <= 10^7
     * TimeMap.set 和 TimeMap.get 函数在每个测试用例中将（组合）调用总计 120000 次。
     */

    class TimeMap {
        Map<String, Map<Integer, String>> stringMapMap;

        /** Initialize your data structure here. */
        public TimeMap() {
            this.stringMapMap = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!stringMapMap.containsKey(key)) {
                TreeMap<Integer, String> val = new TreeMap<>(Comparator.reverseOrder());
                val.put(timestamp, value);
                stringMapMap.put(key, val);
            } else {
                Map<Integer, String> integerStringMap = stringMapMap.get(key);
                integerStringMap.put(timestamp, value);
                stringMapMap.put(key, integerStringMap);
            }
        }

        public String get(String key, int timestamp) {
            if (stringMapMap.containsKey(key)) {
                Iterator<Map.Entry<Integer, String>> iterator = stringMapMap.get(key).entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, String> next = iterator.next();
                    if (next.getKey() <= timestamp) {
                        return next.getValue();
                    }
                }
            }
            return "";
        }
    }
}
