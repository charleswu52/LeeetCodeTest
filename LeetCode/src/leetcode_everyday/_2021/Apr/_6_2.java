package leetcode_everyday._2021.Apr;

/**
 * @author WuChao
 * @since 2021/4/6 上午10:18
 */
public class _6_2 {

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

    private int in = 0;
    private int pre = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return myBuildTree(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode myBuildTree(int[] preorder, int[] inorder, int stop) {
        //pre走完了
        if (pre == preorder.length) {
            return null;
        }
        if (inorder[in] == stop) {//in指针走到了停止点
            in++;//stop点废弃了，in推进一位
            return null;
        }
        TreeNode node = new TreeNode(preorder[pre++]);
        //左子树的停止点是当前的根节点
        node.left = myBuildTree(preorder, inorder, node.val);
        //右子树的停止点是当前树的停止点
        node.right = myBuildTree(preorder, inorder, stop);
        return node;
    }

}
