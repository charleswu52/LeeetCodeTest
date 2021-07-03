package leetcode_everyday.Mar;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author WuChao
 * @since 2021/3/14 上午8:06
 */
public class _14 {
    /**
     * 每日一题：2021/3/14
     * 706. 设计哈希映射
     * 难度: easy
     * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
     *
     * 实现 MyHashMap 类：
     *
     * MyHashMap() 用空映射初始化对象
     * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
     * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
     * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
     * <p>
     * 示例:
     * 输入：
     * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
     * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
     * 输出：
     * [null, null, null, 1, -1, null, 1, null, -1]
     *
     * 解释：
     * MyHashMap myHashMap = new MyHashMap();
     * myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
     * myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
     * myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
     * myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
     * myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
     * myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
     * myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
     * myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
     *
     * <p>
     * 数据范围：
     * 0 <= key, value <= 106
     * 最多调用 104 次 put、get 和 remove 方法
     */


    /**
     * 以空间换时间的方法，预先定义好数组，这种方式仅适用于题目要求，并不是真正意义上的实现HashMap
     */
    class MyHashMap2 {

        private int[] map;

        /**
         * Initialize your data structure here.
         */
        public MyHashMap2() {
            this.map = new int[1000001];
            Arrays.fill(this.map, -1);
        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            this.map[key] = value;
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            return this.map[key];
        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            this.map[key] = -1;
        }
    }

    /**
     * 设计良好的数据结构支持HashMap的具体操作和实现
     */
    class MyHashMap {

        private class Pair {
            private int key;
            private int value;

            public Pair(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public int getKey() {
                return key;
            }

            public void setKey(int key) {
                this.key = key;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

        /**
         * 接下来与13号 {@link _13} 设计HashSet的思路一致，使用数组存储每个的地址，使用链地址法解决数据冲突问题
         */
        private static final int BASE = 769; // 大素数确定数组的长度
        private LinkedList[] data;

        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {
            this.data = new LinkedList[BASE];
            for (int i = 0; i < BASE; i++) {
                data[i] = new LinkedList<Pair>();
            }

        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            int h = hash(key);
            Iterator<Pair> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Pair pair = iterator.next();
                if (pair.getKey() == key) {
                    pair.setValue(value);
                    return;
                }
            }
            data[h].offerLast(new Pair(key, value));
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            int h = hash(key);
            Iterator<Pair> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Pair pair = iterator.next();
                if (pair.getKey() == key) {
                    return pair.getValue();
                }
            }
            return -1;

        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            int h = hash(key);
            Iterator<Pair> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Pair pair = iterator.next();
                if (pair.getKey() == key) {
                    data[h].remove(pair);
                    return;
                }
            }

        }

        private int hash(int key) {
            return key % BASE;
        }
    }


}
