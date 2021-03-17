import java.util.LinkedList;
import java.util.Queue;

/**
 * @author WuChao
 * @since 2021/3/17 上午9:48
 */
public class _28 {
    /**
     * 剑指 Offer 28. 对称的二叉树
     * 难度: easy
     * <p>
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     * <p>
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     * <p>
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * <p>
     * 示例：
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     * <p>
     * 数据范围：
     * 0 <= 节点个数 <= 10000
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

    StringBuilder stringBuilder = new StringBuilder();

    public boolean isSymmetric(TreeNode root) {
        preOrder(root);
        String a = stringBuilder.toString();
        System.out.println(a);
        stringBuilder.setLength(0);
        mirror(root);
        preOrder(root);
        String b = stringBuilder.toString();
        System.out.println(b);


        return a.equals(b);
    }

    public void preOrder(TreeNode treeNode) {
        if (treeNode != null) {
            stringBuilder.append(treeNode.val);
            preOrder(treeNode.left);
            preOrder(treeNode.right);
        } else {
            stringBuilder.append("null");
        }
    }

    // 层序遍历
    public void levelOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            stringBuilder.append(node.val);
//            System.out.print(node.val + "->");

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public void mirror(TreeNode treeNode) {
        if (treeNode != null) {
            TreeNode temp = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = temp;
            mirror(treeNode.left);
            mirror(treeNode.right);
        }
    }

}
