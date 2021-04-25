package chp4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author WuChao
 * @since 2021/4/25 上午9:42
 */
public class _3 {
    /**
     * 程序员面试金典(version 6) - 面试题 04.03. 特定深度节点链表
     * 难度: medium
     * <p>
     * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。
     * 返回一个包含所有深度的链表的数组。
     * <p>
     * 例如：
     * 输入：[1,2,3,4,5,null,7,8]
     *
     *         1
     *        /  \
     *       2    3
     *      / \    \
     *     4   5    7
     *    /
     *   8
     *
     * 输出：[[1],[2,3],[4,5,7],[8]]
     *
     * <p>
     * 数据范围：
     */

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    /*
    题意解析：实际上就是对一颗二叉树进行层序遍历，然后把每一层遍历的结果用链表存储起来
    难点就是在于层数的控制，设置一个size记录每一层
     */
    List<ListNode> listNodes = new ArrayList<>();
    public ListNode[] listOfDepth(TreeNode tree) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        while (queue.size() != 0) {
            int size = queue.size();
            // 定义一个哑节点和cur节点
            ListNode cur = new ListNode(-1);
            ListNode head = cur;// 哑节点
            while (size > 0) {
                TreeNode tmp = queue.poll();
                // 层序遍历 的步骤
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
                // 在此构建链表
                cur.next = new ListNode(tmp.val);
                cur = cur.next;
                size--;
            }
            // 一层完成后 要添加头节点，也就是哑节点的next
            listNodes.add(head.next);
        }
        ListNode[] res = new ListNode[listNodes.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = listNodes.get(i);
        }
        return res;
    }
}
