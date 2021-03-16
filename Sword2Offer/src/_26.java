import java.util.LinkedList;
import java.util.Queue;

/**
 * @author WuChao
 * @since 2021/3/16 上午9:50
 */
public class _26 {
    /**
     * 剑指 Offer 26. 树的子结构
     * 难度: medium
     * <p>
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * <p>
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     * <p>
     * 例如:
     * 给定的树 A:
     * 3
     * / \
     * 4   5
     * / \
     * 1   2
     * 给定的树 B：
     * 4
     * /
     * 1
     * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
     *
     * <p>
     * 示例：
     * 输入：A = [1,2,3], B = [3,1]
     * 输出：false
     * <p>
     * 数据范围：
     * 0 <= 节点个数 <= 10000
     */


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    StringBuilder stringBuilder = new StringBuilder();

    public boolean isSubStructure2(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        // 先对两棵树进行前序遍历
//        preOrder(A);
        levelOrderTraverse(A);
        String a = stringBuilder.toString();
        System.out.println(a);
//        preOrder(B);
        stringBuilder.setLength(0);
        levelOrderTraverse(B);
        String b = stringBuilder.toString();
        System.out.println(b);

        return a.contains(b);


    }

    // 前序遍历二叉树
    private void preOrder(TreeNode treeNode) {
        if (treeNode != null) {
            stringBuilder.append(treeNode.val);
            if (treeNode.left != null) {
                preOrder(treeNode.left);
            }
            if (treeNode.right != null) {
                preOrder(treeNode.right);
            }
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
            System.out.print(node.val + "->");

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    /**
     * 思路重构
     * 若树B是树A的子结构，则子结构的根节点可能为树A的任意一个节点，因此，判断树B是否是树A的子结构，需要完成以下两步工作：
     * 1.先序遍历树中的每个节点nA；对应函数 isSubStructure(A, B)
     * 2.判断树A中以nA为根节点的子树是否包含树B。对应函数 recur(A, B)
     *
     * 名词规定：树 A 的根节点记作 节点 A ，树 B 的根节点称为 节点 B 。
     * recur(A, B) 函数：
     * 1.终止条件：
     *  1.1 当节点 B 为空：说明树 B 已匹配完成（越过叶子节点），因此返回 true ；
     *  1.2 当节点 A 为空：说明已经越过树 A 叶子节点，即匹配失败，返回 false ；
     *  1.3 当节点 A 和 B 的值不同：说明匹配失败，返回 false ；
     * 2.返回值：
     *  2.1 判断 A 和 B 的左子节点是否相等，即 recur(A.left, B.left) ；
     *  2.2 判断 A 和 B 的右子节点是否相等，即 recur(A.right, B.right) ；
     *
     * isSubStructure(A, B) 函数：
     *
     *  1.特例处理： 当 树 A 为空 或 树 B 为空 时，直接返回 false ；
     *  2.返回值： 若树 B 是树 A 的子结构，则必满足以下三种情况之一，因此用或 || 连接；
     *      2.1 以 节点 A 为根节点的子树 包含树 B ，对应 recur(A, B)；
     *      2.2 树 B 是 树 A 左子树 的子结构，对应 isSubStructure(A.left, B)；
     *      2.3 树 B 是 树 A 右子树 的子结构，对应 isSubStructure(A.right, B)；
     *      以上 2. 3. 实质上是在对树 A 做 先序遍历 。
     */

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }
    boolean recur(TreeNode A, TreeNode B) {
        if(B == null) return true;
        if(A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

}
