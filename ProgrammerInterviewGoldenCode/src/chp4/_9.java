package chp4;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/4/28 上午9:45
 */
public class _9 {
    /**
     * 程序员面试金典(version 6) - 面试题 04.09. 二叉搜索树序列
     * 难度: hard
     * <p>
     * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。
     * 给定一个由不同节点组成的二叉搜索树，输出所有可能生成此树的数组。
     *
     * <p>
     * 示例：
     * 给定如下二叉树
     *
     *         2
     *        / \
     *       1   3
     * 返回：
     * [
     *    [2,1,3],
     *    [2,3,1]
     * ]
     * <p>
     * 数据范围：
     */

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    /*
    题意解析：给出一棵二叉搜索树，然后找出所有可能生成该二叉树的数组
     */

    /*
    考虑树的结构的话，对每一个节点有两种遍历方法，先找左子树还是有子树，因此每个节点都有这两种选择的话吗，那分支数量还是非常的多

   分析下题目 中表达的所有可能生成该树的数组，反过来说就是遍历该二叉树的所有方式，自然而然想到的就是递归和回溯.
     每次选择一个结点之后，我们都需要在候选节点队列里面添加其左右孩子，因为只要走过当前结点了，那么其孩子都会变成可达的。
     因此我们的回溯算法除了要维护一个path用来保存路径之外，还需要额外维护一个候选节点队列dq。
     使用双端队列来维护候选队列，这样每次插入和回溯的时间复杂度都可以降到O(1).
     */

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> BSTSequences(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        if (root != null) {
            deque.offer(root);
        }
        dfs(deque);
        return res;

    }

    // 1.确定递归函数返回值和参数
    public void dfs(Deque<TreeNode> deque) {
        // 2. 确定递归终止条件
        // dp 保存的是该层剩下的可选节点的候选队列，若队列为空说明没有候选元素了
        // 因此当队列为空的时候直接把当前路径添加到结果集，然后返回
        if (deque.isEmpty()) {
            res.add(new ArrayList<>(path));
        }

        // 3.确定回溯函数的遍历过程
        // 当前层与可选的候选节点的个数
        int size = deque.size();
        while (size > 0) {
            TreeNode cur = deque.pollFirst();
            // 向路径中添加当前值
            path.add(cur.val);
            // 记录添加的子节点数量，等回溯的时候要用
            int children = 0;
            if (cur.left != null) {
                children++;
                deque.offerLast(cur.left);
            }
            if (cur.right != null) {
                children++;
                deque.offerLast(cur.right);
            }

            // 开始递归
            dfs(deque);

            // 回溯候选队列
            while (children > 0) {
                deque.pollLast();
                children--;
            }
            deque.offerLast(cur);
            // 回溯路径
            path.removeLast();
            // 当前节点处理完毕，数量减一
            size--;
        }
    }
}
