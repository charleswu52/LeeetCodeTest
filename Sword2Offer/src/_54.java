import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/26 下午3:21
 */
public class _54 {
    /**
     * 剑指 Offer 54. 二叉搜索树的第k大节点
     * 难度: easy
     * <p>
     * 给定一棵二叉搜索树，请找出其中第k大的节点。
     *
     * <p>
     * 示例：
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 4
     *
     * <p>
     * 数据范围：
     * 1 ≤ k ≤ 二叉搜索树元素个数
     */

    /**
     * 二叉树的定义
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 初始思路：对二叉搜索树进行中序遍历
     *
     * @param root
     * @param k
     * @return
     */
    List<Integer> store;

    //        store = new ArrayList<>();
//        inorder(root);
//        return store.get(store.size() - k);
    int res, k;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        inorder(root);
        return res;
    }


    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            store.add(root.val);
            inorder(root.right);

        }
    }

    public void inorder2(TreeNode root) {

        if (root != null) {
            inorder2(root.right);
            if (k == 0) {
                return;
            }

            if (--k == 0) {
                res = root.val;
            }
            inorder2(root.left);
        }

    }
}
