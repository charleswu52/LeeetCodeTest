package leetcode_everyday._2022.Apr;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author WuChao
 * @create 2022/4/9 10:34
 */
public class _8_429 {
    /**
     * 每日一题：2022/4/8
     * <p>
     * 429. N 叉树的层序遍历
     * <p>
     * 难度：medium
     * <p>
     * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
     * <p>
     * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
     * <p>
     * 示例
     * <p>
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：[[1],[3,2,4],[5,6]]
     * <p>
     * 范围
     * <p>
     * 树的高度不会超过 1000
     * 树的节点总数在 [0, 10^4] 之间
     */

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    /*
    思路： 广度优先搜索BFS
     */

    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (Node node1 : node.children) {
                    queue.add(node1);
                }
                list.add(node.val);

            }
            res.add(list);

        }
        return res;
    }

}
