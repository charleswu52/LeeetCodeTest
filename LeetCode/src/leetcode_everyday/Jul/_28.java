package leetcode_everyday.Jul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/7/28 8:15
 */
public class _28 {
    /**
     * <p> 每日一题：2021/7/28 </p>
     * <p> 863. 二叉树中所有距离为 K 的结点  </p>
     * <p> 难度: medium </p>
     *
     * <p>
     * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
     *
     * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
     *
     * </p>
     * <p>示例</p>
     * <p>
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
     * 输出：[7,4,1]
     * 解释：
     * 所求结点为与目标结点（值为 5）距离为 2 的结点，
     * 值分别为 7，4，以及 1
     *
     * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
     * 上面的输入仅仅是对这些对象进行了序列化描述。
     *
     * <p> 范围 </p>
     * <p>
     * 给定的树是非空的。
     * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
     * 目标结点 target 是树上的结点。
     * 0 <= K <= 1000.
     * </p>
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
    思路：DFS + 哈希表

    从target节点出发进行深度优先搜索，可以向下（左右子节点）进行搜索找到与当前节点距离为k的节点
    然后还可以向上搜索，通过父亲节点进行搜，因此可以用一个哈希表存储每个节点对应的父节点，因为题目中说每个节点的值都是唯一的，因此key直接用节点值
    同时在深度搜索过程中为了避免深度优先搜索时重复访问的问题，在递归时要传入一个from 节点，判断当前访问节点是否与之前的节点重复
     */

    List<Integer> res = new ArrayList<>();
    Map<Integer, TreeNode> parent = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        findParent(root);
        findAns(target, null, 0, k);

        return res;
    }

    public void findParent(TreeNode node) {
        if (node.left != null) {
            parent.put(node.left.val, node);
            findParent(node.left);
        }
        if (node.right != null) {
            parent.put(node.right.val, node);
            findParent(node.right);
        }
    }

    public void findAns(TreeNode node, TreeNode from, int depth, int k) {
        if (node == null) {
            return;
        }
        if (depth == k) {
            res.add(node.val);
            return;
        }
        if (node.left != from) {
            findAns(node.left, node, depth + 1, k);
        }
        if (node.right != from) {
            findAns(node.right, node, depth + 1, k);
        }
        if (parent.get(node.val) != from) {
            findAns(parent.get(node.val), node, depth + 1, k);
        }

    }

}
