package leetcodetest.May;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @since 2021/5/16 8:36
 */
public class _16 {
    /**
     * 每日一题：2021/5/16
     * 421. 数组中两个数的最大异或值
     * 难度: medium
     * <p>
     * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
     * <p>
     * 进阶：你可以在 O(n) 的时间解决这个问题吗？
     * <p>
     * 示例：
     * 输入：nums = [3,10,5,25,2,8]
     * 输出：28
     * 解释：最大运算结果是 5 XOR 25 = 28.
     * <p>
     * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
     * 输出：127
     * <p>
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 2 * 104
     * 0 <= nums[i] <= 231 - 1
     */

    /*
    题目解析：
    难点：在O(n)的时间复杂度内解决
    根据按位异或运算的性质，x = a_i ⊕ a_j 等价于 a_j = x ⊕ a_i 。我们可以根据这一变换，设计一种「从高位到低位依次确定 x 二进制表示的每一位」
    的方法，以此得到 x 的值。该方法的精髓在于：
    由于数组中的元素都在 [0, 2^31) 的范围内，那么我们可以将每一个数表示为一个长度为 31 位的二进制数（如果不满 31 位，在最高位之前补上若干个前导 0 即可）；
    这 31 个二进制位从低位到高位依次编号为 0,1,⋯,30。我们从最高位第 30 个二进制位开始，依次确定 x 的每一位是 0 还是 1；
    由于我们需要找出最大的 x，因此在枚举每一位时，我们先判断 x 的这一位是否能取到 1。如果能，我们取这一位为 1，否则我们取这一位为 0。
    「判断 x 的某一位是否能取到 1」这一步骤并不容易。下面介绍两种判断的方法。
     */

    /*
    思路1: 哈希表
    假设我们已经确定了 x 最高的若干个二进制位，当前正在确定第 k 个二进制位。根据「题目解析」部分的分析，我们希望第 k 个二进制位能够取到 1。
    我们用 pre^k(x) 表示 x 从最高位第 30 个二进制位开始，到第 k 个二进制位为止的数，那么 a_j = x ⊕ a_i 蕴含着：
    pre^k(a_j) = pre^k(x)  ⊕ pre^k(a_i);由于 pre^k(x)对于我们来说是已知的，因此我们将所有的 pre^k(a_j) 放入哈希表中，随后枚举 i
    并计算 pre^k(x) ⊕ pre^k(a_i)。如果其出现在哈希表中，那么说明第 k 个二进制位能够取到 1，否则第 k 个二进制位只能为 0。
     */
    static final int HIGH_BIT = 30;

    public int findMaximumXOR1(int[] nums) {
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            Set<Integer> seen = new HashSet<>();
            // 将所有的 pre^k(a_j) 放入哈希表中
            for (int num : nums) {
                // 如果只想保留从最高位开始到第 k 个二进制位为止的部分
                // 只需将其右移 k 位
                seen.add(num >> k);
            }
            // 目前 x 包含从最高位开始到第 k+1 个二进制位为止的部分
            // 我们将 x 的第 k 个二进制位置为 1，即为 x = x*2+1
            int xNext = x * 2 + 1;
            boolean found = false;

            // 枚举 i
            for (int num : nums) {
                if (seen.contains(xNext ^ (num >> k))) {
                    found = true;
                    break;
                }
            }

            if (found) {
                x = xNext;
            } else {
                // 如果没有找到满足等式的 a_i 和 a_j，那么 x 的第 k 个二进制位只能为 0
                // 即为 x = x*2
                x = xNext - 1;
            }
        }
        return x;


    }

    /*
    思路2：字典树
    我们也可以将数组中的元素看成长度为 31 的字符串，字符串中只包含 0 和 1。如果我们将字符串放入字典树中，那么在字典树中查询一个字符串的过程，
    恰好就是从高位开始确定每一个二进制位的过程。字典树的具体逻辑以及实现可以参考「208. 实现 Trie（前缀树）的官方题解」，
    这里我们只说明如何使用字典树解决本题。

    根据 x = a_i ⊕ a_j ,我们枚举 a_i，并将 a_0, a_1,..., a_i-1 作为 a_j 放入字典树中，希望找出使得 x 达到最大值的 a_j。
    如何求出 x 呢？我们可以从字典树的根节点开始进行遍历，遍历的「参照对象」为 a_i。在遍历的过程中，我们根据 a_i 的第 x 个二进制位是 0 还是 1，
    确定我们应当走向哪个子节点以继续遍历。假设我们当前遍历到了第 k 个二进制位：
        如果 a_i 的第 k 个二进制位为 0，那么我们应当往表示 1 的子节点走，这样 0 ⊕ 1=1，可以使得 x 的第 k 个二进制位为 1。
            如果不存在表示 1 的子节点，那么我们只能往表示 0 的子节点走，x 的第 k 个二进制位为 0；
        如果 a_i 的第 k 个二进制位为 1，那么我们应当往表示 0 的子节点走，这样 1⊕0=1，可以使得 x 的第 kk 个二进制位为 1。
            如果不存在表示 0 的子节点，那么我们只能往表示 1 的子节点走，x 的第 k 个二进制位为 0。
    当遍历完所有的 31 个二进制位后，我们也就得到了 a_i 可以通过异或运算得到的最大 x。这样一来，如果我们枚举了所有的 a_i，也就得到了最终的答案。

    细节
    由于字典树中的每个节点最多只有两个子节点，分别表示 0 和 1，因此本题中的字典树是一棵二叉树。在设计字典树的数据结构时，我们可以令左子节点
    left 表示 0，右子节点 right 表示 1。
     */

    // 前缀树
    class Trie {
        // 左子树指向表示 0 的子节点
        Trie left = null;
        // 右子树指向表示 1 的子节点
        Trie right = null;
    }

    Trie root = new Trie();

    public int findMaximumXOR2(int[] nums) {
        int n = nums.length;
        int x = 0;
        for (int i = 1; i < n; i++) {
            // 将nums[i-1]放入字典树，此时nums[0..i-1]都在字典树中
            add(nums[i - 1]);
            // 将nums[i]看做a_i 找出更大x 更新答案
            x = Math.max(x, check(nums[i]));
        }
        return x;

    }

    public void add(int num) {
        Trie cur = root;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (cur.left == null) {
                    cur.left = new Trie();
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new Trie();
                }
                cur = cur.right;
            }
        }
    }

    public int check(int num) {
        Trie cur = root;
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                //a_i 的第k个二进制位是0，应该往表示1的子节点right走
                if (cur.right != null) {
                    cur = cur.right;
                    x = x * 2 + 1;
                } else {
                    cur = cur.left;
                    x = x * 2;
                }
            } else {
                //a_i 的第k个二进制位是1，应该往表示0的子节点left走
                if (cur.left != null) {
                    cur = cur.left;
                    x = x * 2 + 1;
                } else {
                    cur = cur.right;
                    x = x * 2;
                }
            }

        }
        return x;
    }


    @Test
    public void test() {
        int[] nums = {14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70};
        System.out.println(findMaximumXOR1(nums));


    }
}
