package leetcode_everyday.Mar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/13 上午7:56
 */
public class _13 {
    /**
     * 每日一题：2021/3/13
     * 705. 设计哈希集合
     * 难度: easy
     * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
     *
     * 实现 MyHashSet 类：
     * void add(key) 向哈希集合中插入值 key 。
     * bool contains(key) 返回哈希集合中是否存在这个值 key 。
     * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
     * <p>
     * 示例:
     * 输入：
     * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
     * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
     * 输出：
     * [null, null, null, true, false, null, true, null, false]
     *
     * 解释：
     * MyHashSet myHashSet = new MyHashSet();
     * myHashSet.add(1);      // set = [1]
     * myHashSet.add(2);      // set = [1, 2]
     * myHashSet.contains(1); // 返回 True
     * myHashSet.contains(3); // 返回 False ，（未找到）
     * myHashSet.add(2);      // set = [1, 2]
     * myHashSet.contains(2); // 返回 True
     * myHashSet.remove(2);   // set = [1]
     * myHashSet.contains(2); // 返回 False ，（已移除）
     *
     * <p>
     * 数据范围：
     * 0 <= key <= 10^6
     * 最多调用 10^4 次 add、remove 和 contains 。
     */


    /**
     * 自定义实现HashSet
     */
    class MyHashSet {

        private List<Integer> keyList;

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {
            this.keyList = new ArrayList<>();
        }

        public void add(int key) {
            if (!contains(key)) {
                this.keyList.add(key);
            }

        }

        public void remove(int key) {
            for (int i = 0; i < keyList.size(); i++) {
                if (keyList.get(i) == key) {
                    keyList.remove(i);
                    break;
                }
            }
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            return keyList.contains(key);
        }
    }

    // 不选择投机取巧的使用常用类的库，真正从HashSet的底层结构中去设计HashSet的实现
    /**
     * 关键问题
     * 1. 哈希函数：能够将集合中任意可能的元素映射到一个固定范围的整数值，并将该元素存储到整数值对应的地址上。
     * 2. 冲突处理：不同元素可能映射到相同的整数值，因此需要在整数值出现「冲突」时，需要进行冲突处理。
     *      链地址法：为每个哈希值维护一个链表，并将具有相同哈希值的元素都放入这一链表当中。
     *      开放地址法：当发现哈希值 h 处产生冲突时，根据某种策略，从 h 出发找到下一个不冲突的位置。
     *                例如，一种最简单的策略是，不断地检查 h+1,h+2,h+3,… 这些整数对应的位置。
     *      再哈希法：当发现哈希冲突后，使用另一个哈希函数产生一个新的地址。
     * 3. 扩容：当哈希表元素过多时，冲突的概率将越来越大，而在哈希表中查询一个元素的效率也会越来越低。
     *         因此，需要开辟一块更大的空间，来缓解哈希表中发生的冲突。
     *
     */
    /**
     * 这里使用链地址法来解决冲突问题
     * 设哈希表的大小为 base，则可以设计一个简单的哈希函数：hash(x)=x mod base。
     * 我们开辟一个大小为 base 的数组，数组的每个位置是一个链表。当计算出哈希值之后，就插入到对应位置的链表当中。
     * 由于我们使用整数除法作为哈希函数，为了尽可能避免冲突，应当将 base 取为一个质数。在这里，我们取base=769。
     */

    class MyHashSet2 {
        private static final int BASE = 769;
        private LinkedList[] data;

        public MyHashSet2() {
            this.data = new LinkedList[BASE];
            for (int i = 0; i < BASE; i++) {
                data[i] = new LinkedList<Integer>();
            }
        }

        public void add(int key) {
            int h = hash(key);
            Iterator<Integer> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Integer element = iterator.next();
                if (element == key) {
                    return;
                }
            }
            data[h].offerLast(key);
        }

        public void remove(int key) {
            int h = hash(key);
            Iterator<Integer> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Integer element = iterator.next();
                if (element == key) {
                    data[h].remove(element);
                    return;
                }
            }
        }

        public boolean contains(int key) {
            int h = hash(key);
            Iterator<Integer> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Integer element = iterator.next();
                if (element == key) {
                    return true;
                }
            }
            return false;

        }

        private int hash(int key) {
            return key % BASE;
        }
    }


}
