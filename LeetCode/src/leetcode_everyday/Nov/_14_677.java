package leetcode_everyday.Nov;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/11/14 9:42
 */
public class _14_677 {
    /**
     * 每日一题：2021/11/14
     * <p>
     * 677. 键值映射
     * <p>
     * 难度：medium
     * <p>
     * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
     * <p>
     * MapSum() 初始化 MapSum 对象
     * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。
     * 如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
     * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * <p>
     * ["MapSum", "insert", "sum", "insert", "sum"]
     * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
     * <p>
     * 输出：
     * <p>
     * [null, null, 3, null, 5]
     * <p>
     * 解释：
     * <p>
     * MapSum mapSum = new MapSum();
     * mapSum.insert("apple", 3);
     * mapSum.sum("ap");           // return 3 (apple = 3)
     * mapSum.insert("app", 2);
     * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
     * <p>
     * 范围
     * <p>
     * 1 <= key.length, prefix.length <= 50
     * key 和 prefix 仅由小写英文字母组成
     * 1 <= val <= 1000
     * 最多调用 50 次 insert 和 sum
     */

    /*
    思路：前缀树
     */

    class MapSum {

        private Map<String, Integer> map;
        private Trie root;

        public MapSum() {
            this.map = new HashMap<>();
            this.root = new Trie();
        }

        public void insert(String key, int val) {
            int delta = val - map.getOrDefault(key, 0);
            this.map.put(key, val);
            Trie node = this.root;
            for (char c : key.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new Trie();
                }
                node = node.children[c - 'a'];
                node.val += delta;
            }
        }

        public int sum(String prefix) {
            Trie node = this.root;
            for (char c : prefix.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    return 0;
                }
                node = node.children[c - 'a'];
            }
            return node.val;

        }
    }

    class Trie{
        int val;
        Trie[] children;

        public Trie() {
            this.val = 0;
            this.children = new Trie[26];
        }
    }
}
