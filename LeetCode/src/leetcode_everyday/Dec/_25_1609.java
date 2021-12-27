package leetcode_everyday.Dec;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author WuChao
 * @create 2021/12/25 上午9:35
 */
public class _25_1609 {
    /**
     * 每日一题：2021/12/25
     * <p>
     * 1705. 吃苹果的最大数目
     * <p>
     * 难度：medium
     * <p>
     * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
     *
     * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
     * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
     * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
     * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
     * 输出：true
     * 解释：每一层的节点值分别是：
     * 0 层：[1]
     * 1 层：[10,4]
     * 2 层：[3,7,9]
     * 3 层：[12,8,6,2]
     * 由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/even-odd-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 范围
     * <p>
     * apples.length == n
     * days.length == n
     * 1 <= n <= 2 * 10^4
     * 0 <= apples[i], days[i] <= 2 * 10^4
     * 只有在 apples[i] = 0 时，days[i] = 0 才成立
     **/

    /**
     * 二叉树节点定义
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /*
    思路： BFS 搜索
     */

    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size != 1) {
                int[] ints = new int[size];
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    ints[i] = node.val;
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                if (level % 2 == 0) {
                    for (int i = 0; i < size; i++) {
                        if (ints[i] % 2 == 0 || (i + 1 < size && ints[i + 1] % 2 == 0) || (i + 1 < size && ints[i + 1] <= ints[i])) {
                            return false;
                        }
                    }
                } else {
                    for (int i = 0; i < size; i++) {
                        if (ints[i] % 2 == 1 || (i + 1 < size && ints[i + 1] % 2 == 1) || (i + 1 < size && ints[i + 1] >= ints[i])) {
                            return false;
                        }
                    }
                }
            } else {
                TreeNode node = queue.poll();
                if (level % 2 == node.val % 2  ) {
                    return false;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
        }
        return true;

    }

    @Test
    public void test() {
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(12);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(18);
        TreeNode node7 = new TreeNode(16);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node4.right = node7;

        System.out.println(isEvenOddTree(node1));

    }
}
