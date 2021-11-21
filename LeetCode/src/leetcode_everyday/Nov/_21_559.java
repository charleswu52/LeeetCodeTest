package leetcode_everyday.Nov;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author WuChao
 * @create 2021/11/21 10:04
 */
public class _21_559 {
    /**
     * 每日一题：2021/11/21
     * <p>
     * 559. N 叉树的最大深度
     * <p>
     * 难度：easy
     * <p>
     * 给定一个 N 叉树，找到其最大深度。
     *
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     *
     * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
     *
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：3
     *
     * <p>
     * 范围
     * <p>
     * 树的深度不会超过 1000 。
     * 树的节点数目位于 [0, 10^4] 之间。
     */

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /*
    思路1：DFS 深度遍历
     */

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.children == null) {
            return 1;
        }
        int res = 0;
        for (Node chi : root.children) {
            res = Math.max(res, maxDepth(chi));
        }
        return res + 1;
    }

    /*
    思路2：BFS 广度遍历
     */

    public int maxDepth2(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.children == null) {
            return 1;
        }
        int res = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Node node = queue.poll();
                if (node.children != null) {
                    for (Node chi : node.children) {
                        queue.offer(chi);
                    }
                }
                size--;
            }
            res++;
        }
        return res;

    }


    }
