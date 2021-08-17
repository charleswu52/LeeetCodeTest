package leetcode_hot100.top60;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/7/15 13:51
 */
public class _146 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 146. LRU 缓存机制
     * 难度：medium
     * <p>
     * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
     * <p>
     * 实现 LRUCache 类：
     * <p>
     * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
     * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     * <p>
     * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
     * <p>
     * 示例：
     * 输入
     * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
     * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
     * 输出
     * [null, null, null, 1, null, -1, null, -1, 3, 4]
     * <p>
     * 解释
     * LRUCache lRUCache = new LRUCache(2);
     * lRUCache.put(1, 1); // 缓存是 {1=1}
     * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
     * lRUCache.get(1);    // 返回 1
     * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
     * lRUCache.get(2);    // 返回 -1 (未找到)
     * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
     * lRUCache.get(1);    // 返回 -1 (未找到)
     * lRUCache.get(3);    // 返回 3
     * lRUCache.get(4);    // 返回 4
     *
     * <p>
     * 数据范围:
     * 1 <= capacity <= 3000
     * 0 <= key <= 10000
     * 0 <= value <= 105
     * 最多调用 2 * 105 次 get 和 put
     */

    /*
    哈希表+双向链表
    相当于是手写 LinkedHashMap

    注意采用泛型写法
     */

    class LRUCache<K,V> {

        // 双向链表节点
        class DLinkedNode {
            // 必须存 key 为了删除用
            K key;
            V value;
            DLinkedNode pre;
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private Map<K, DLinkedNode> cahce;
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache(int capacity) {
            this.cahce = new HashMap<>();
            this.size = 0;
            this.capacity = capacity;
            // 使用伪头部和伪尾部节点
            this.head = new DLinkedNode();
            this.tail = new DLinkedNode();

            this.head.next = this.tail;
            this.tail.pre = this.head;

        }

        public V get(K key) {
            DLinkedNode node = cahce.get(key);
            if (node == null) {
                return null;
            }
            // 如果 key 存在，先通过哈希表定位，再移到头部
            moveToHead(node);
            return node.value;
        }

        public void put(K key, V value) {
            DLinkedNode node = cahce.get(key);
            if (node == null) {
                // 如果不存在，创建一个新的
                DLinkedNode newNode = new DLinkedNode(key, value);
                // 添加进哈希表
                cahce.put(key, newNode);
                // 添加至双向链表的头部
                addTohead(newNode);
                size++;
                if (size > capacity) {
                    // 如果超出容量，删除双向链表的尾部节点
                    DLinkedNode tail = removeTail();
                    // 删除哈希表中对应的项
                    cahce.remove(tail.key);
                    size--;
                }
            } else {
                // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
                node.value = value;
                moveToHead(node);

            }
        }

        /**
         * 添加到双向链表头部
         * @param node
         */
        private void addTohead(DLinkedNode node) {
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }

        /**
         * 移除普通节点
         * @param node
         */
        private void removeNode(DLinkedNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        /**
         * 移动节点到头部
         * @param node
         */
        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addTohead(node);
        }

        /**
         * 移除尾部节点
         * @return
         */
        private DLinkedNode removeTail() {
            DLinkedNode res = tail.pre;
            removeNode(res);
            return res;
        }
    }



}
