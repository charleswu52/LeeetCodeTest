package leetcode_everyday._2021.May;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author WuChao
 * @since 2021/5/23 上午8:32
 */
public class _23 {
    /**
     * 每日一题：2021/5/23
     * 1707. 与数组中元素的最大异或值
     * 难度: hard
     * <p>
     * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
     * <p>
     * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
     * <p>
     * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
     * <p>
     * 示例：
     * 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
     * 输出：[3,3,7]
     * 解释：
     * 1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
     * 2) 1 XOR 2 = 3.
     * 3) 5 XOR 2 = 7.
     * <p>
     * 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
     * 输出：[15,-1,5]
     * <p>
     * 数据范围：
     * 1 <= nums.length, queries.length <= 105
     * queries[i].length == 2
     * 0 <= nums[j], xi, mi <= 109
     */

    /*
    思路1：朴素解法
    问题：超时
     */
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = queries.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int x = queries[i][0];
            int m = queries[i][1];
            for (int j = 0; j < nums.length && nums[j] <= m; j++) {
                res[i] = Math.max(res[i], x ^ nums[j]);
            }
        }
        return res;
    }

    /*
    思路2：
    使用前缀树（字典树）Trie
    参考题解：
    https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array/solution/yu-shu-zu-zhong-yuan-su-de-zui-da-yi-huo-7erc/
     */
    class Trie {
        static final int L = 30;
        Trie[] children = new Trie[2];// 只有 0 1 两个子节点

        public void insert(int val) {
            Trie node = this;
            for (int i = L - 1; i >= 0; i--) {
                int bit = (val >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new Trie();
                }
                node = node.children[bit];
            }
        }

        public int getMaxXor(int val) {
            int ans = 0;
            Trie node = this;
            for (int i = L - 1; i >= 0; i--) {
                int bit = (val >> i) & 1;
                if (node.children[bit ^ 1] != null) {
                    ans |= 1 << i;
                    bit ^= 1;
                }
                node = node.children[bit];
            }
            return ans;
        }
    }

    public int[] maximizeXor2(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        int numQ = queries.length;
        int[][] newQueries = new int[numQ][3];
        for (int i = 0; i < numQ; i++) {
            newQueries[i][0] = queries[i][0];
            newQueries[i][1] = queries[i][1];
            newQueries[i][2] = i; // 记录原查询的一个下标
        }
        // 对query的m从小到达排序
        Arrays.sort(newQueries, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int[] ans = new int[numQ];
        Trie trie = new Trie();
        int idx = 0, n = nums.length;
        for (int[] query : newQueries) {
            int x = query[0], m = query[1], qid = query[2];
            while (idx < n && nums[idx] <= m) {
                trie.insert(nums[idx]);
                idx++;
            }
            if (idx == 0) {// 字典树为空
                ans[qid] = -1;
            } else {
                ans[qid] = trie.getMaxXor(x);
            }
        }
        return ans;
    }


}
