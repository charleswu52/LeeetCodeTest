package chp17;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/6/7 上午10:55
 */
public class _12 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.12. BiNode
     * 难度: easy
     * <p>
     * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
     * 实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
     * <p>
     * 返回转换后的单向链表的头节点。
     * <p>
     * 注意：本题相对原题稍作改动
     * <p>
     * 示例:
     * 输入： [4,2,5,1,3,null,6,0]
     * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
     * <p>
     * 数据范围：
     * 节点数量不会超过 100000。
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    List<TreeNode> store;
    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) {
            return root;
        }
        store = new ArrayList<>();
        inorder(root);
        int i = 0;
        for (i = 0; i < store.size()-1; i++) {
            store.get(i).left = null;
            store.get(i).right = store.get(i + 1);
        }
        store.get(i).left = null;
        store.get(i).right = null;
        return store.get(0);
    }

    public void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            store.add(node);
            inorder(node.right);

        }
    }

    /*
    思路2 ： 记录前驱节点
     */
    TreeNode head = new TreeNode(-1);   // 多设置的一个节点
    TreeNode pre = null;   // 记录前驱节点
    public TreeNode convertBiNode2(TreeNode root) {
        convertBiNode2(root);
        return head.right;

    }

    public void inorder2(TreeNode node) {
        if (node != null) {
            inorder2(node.left);

            if (pre == null) {
                pre = node;
                head.right = node;
            } else {
                pre.right = node;
                pre = node;
            }
            node.left = null;

            inorder2(node.right);
        }
    }
}
