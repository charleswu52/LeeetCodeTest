import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author WuChao
 * @since 2021/4/3 上午11:27
 */
public class _68_2 {
    /**
     * 剑指 Offer 68 - II. 二叉树的最近公共祖先
     * 难度: easy
     * <p>
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先
     * 且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * <p>
     * 例如：
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出: 5
     * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
     *
     *
     * <p>
     * 数据范围：
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉搜索树中
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

    /*
    题目解析：
    与 _68_1 题目类似 只不过这里的二叉树是普通的二叉树不再具有二叉搜索树的性质
     */

    /**
     * 思路1：存储父节点
     * 遍历二叉树，使用哈希表存储所有节点的父节点，然后就可以利用节点的父节点信息从p节点开始不断往上跳，并记录已经访问过的节点，
     * 再从q节点开始不断往上跳，如果碰到已经访问过的节点，那么这个节点就是我们要找的最近公共祖先。
     */

    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>(); // 由于每个节点的值都是唯一的，因此可以用集合存储

    /**
     * 从根节点开始遍历二叉树，存储每个节点对应的根节点
     * @param root
     */
    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root); // 从根节点遍历二叉树
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }
}
