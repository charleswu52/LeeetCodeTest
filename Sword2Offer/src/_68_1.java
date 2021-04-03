import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/4/3 上午10:32
 */
public class _68_1 {
    /**
     * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
     * 难度: easy
     * <p>
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先
     * 且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * <p>
     * 例如：
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * 输出: 2
     * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
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


    /**
     * 思路1： 两次遍历
     * 从根节点开始搜索，分别得到到p、q两个节点的路径
     * 然后比较这两个节点路径上的靠后的公共节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = getPath(root, p);
        List<TreeNode> qPath = getPath(root, q);

        // 遍历两个路径，找到靠后的公共节点
        TreeNode ans = null;
        for (int i = 0; i < pPath.size() && i < qPath.size(); i++) {
            if (pPath.get(i) == qPath.get(i)) {
                ans = pPath.get(i);
            } else {
                break; // 遇到分叉点之后的节点便不会相同了
            }
        }
        return ans;

    }

    public List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<>();
        TreeNode node = root;
        while (node != target) {
            path.add(node);
            // 利用二叉搜索树的特点 比较两个节点的值
            if (node.val < target.val) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        // 最后得到的就是目标节点
        path.add(node);
        return path;
    }

    /*
    优化思路2： 利用二叉搜索树的性质，一次遍历两个
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = root;
        while (true) {
            if (p.val < ans.val && q.val < ans.val) {
                ans = ans.left;
            } else if (p.val > ans.val && q.val > ans.val) {
                ans = ans.right;
            } else {
                break;
            }
        }
        return ans;
    }


    }
