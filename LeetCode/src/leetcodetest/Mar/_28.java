package leetcodetest.Mar;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/28 上午7:47
 */
public class _28 {
    /**
     * 每日一题：2021/3/28
     * 173. 二叉搜索树迭代器
     * 难度: medium
     * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
     * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
     * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
     * int next()将指针向右移动，然后返回指针处的数字。
     * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
     * <p>
     * 示例：
     * 输入
     * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
     * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
     * 输出
     * [null, 3, 7, true, 9, true, 15, true, 20, false]
     * <p>
     * 解释
     * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
     * bSTIterator.next();    // 返回 3
     * bSTIterator.next();    // 返回 7
     * bSTIterator.hasNext(); // 返回 True
     * bSTIterator.next();    // 返回 9
     * bSTIterator.hasNext(); // 返回 True
     * bSTIterator.next();    // 返回 15
     * bSTIterator.hasNext(); // 返回 True
     * bSTIterator.next();    // 返回 20
     * bSTIterator.hasNext(); // 返回 False
     *
     * <p>
     * 数据范围：
     * 树中节点的数目在范围 [1, 10^5] 内
     * 0 <= Node.val <= 106
     * 最多调用 10^5 次 hasNext 和 next 操作
     */

    /**
     * 二叉树的定义
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

    /**
     * 构造二叉搜索树迭代器
     * 方法1：在初始化迭代器的时候，对传入的二叉搜索树进行中序遍历，并且把遍历结果保存到list中
     * 这种方法也叫做扁平化处理
     */
    class BSTIterator {

        List<Integer> treeNodeList;
        int count;
        int idx;

        public BSTIterator(TreeNode root) {
            this.treeNodeList = new ArrayList<>();
            inorder(root);
            count = treeNodeList.size();
            if (count == 0) {
                idx = -1;
            } else {
                idx = 0;
            }
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public int next() {
            return this.treeNodeList.get(idx++);
        }

        public boolean hasNext() {
            return this.getIdx() >= 0 && this.getIdx() < this.getCount();
        }

        private void inorder(TreeNode node) {
            if (node != null) {
                inorder(node.left);
                treeNodeList.add(node.val);
                inorder(node.right);
            }
        }
    }

    /**
     * 方法2：迭代
     * 利用栈，通过迭代的方式对二叉树做中序遍历。此时，无需计算出中序遍历的全部结果，只需要实时维护当前栈的情况即可。
     */
    class BSTIterator2 {

        private TreeNode cur;
        private Deque<TreeNode> stack;

        public BSTIterator2(TreeNode root) {
            cur = root;
            stack = new LinkedList<>();
        }


        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            int ret = cur.val;
            cur = cur.right;
            return ret;
        }

        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
        }

    }
}
