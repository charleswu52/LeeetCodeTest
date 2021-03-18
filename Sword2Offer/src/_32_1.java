import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author WuChao
 * @since 2021/3/18 上午9:54
 */
public class _32_1 {
    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     * 难度: medium
     * <p>
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     * <p>
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     * 输出：[3,9,20,15,7]
     * <p>
     * 数据范围：
     * 节点总数 <= 1000
     */

    // 二叉树的定义
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int[] levelOrder(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return new int[]{};
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        // 不使用遍历赋值的方式将list转化为int[]
        return list.stream().mapToInt(Integer::intValue).toArray();

    }

}
