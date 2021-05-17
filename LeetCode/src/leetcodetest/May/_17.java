package leetcodetest.May;

/**
 * @author WuChao
 * @since 2021/5/17 下午7:00
 */
public class _17 {
    /**
     * 每日一题：2021/5/16
     * 993. 二叉树的堂兄弟节点
     * 难度: easy
     * <p>
     * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
     * <p>
     * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
     * <p>
     * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
     * <p>
     * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
     * <p>
     * 示例：
     * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
     * 输出：true
     * <p>
     * 输入：root = [1,2,3,null,4], x = 2, y = 3
     * 输出：false
     * <p>
     *
     * <p>
     * 数据范围：
     * 二叉树的节点数介于 2 到 100 之间。
     * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
     */

    /*
    二叉树节点定义
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
    思路：使用深搜。
    遍历过程中记录深度和父节点，找到x和y的节点后，判断两个节点是否处于同一层并且父节点不同
     */
    int x;
    TreeNode xParent;
    int xDepth;
    boolean xFound=false;

    int y;
    TreeNode yParent;
    int yDepth;
    boolean yFound=false;

    public boolean isCousins(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        dfs(root, 0, null);
        return xDepth == yDepth && xParent != yParent;

    }

    public void dfs(TreeNode node, int depth, TreeNode parent) {
        if (node == null) {
            return;
        }
        if (node.val == x) {
            xParent = parent;
            xDepth = depth;
            xFound = true;
        } else if (node.val == y) {
            yParent = parent;
            yDepth = depth;
            yFound = true;
        }
        if (xFound && yFound) {
            return;
        }
        dfs(node.left, depth+1, node);
        if (xFound && yFound) {
            return;
        }
        dfs(node.right, depth+1, node);


    }
}
