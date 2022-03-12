package leetcode_everyday._2022.Mar;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/3/12 9:25
 */
public class _12_590 {
    /**
     * 每日一题：2022/3/12
     * <p>
     * 590. N 叉树的后序遍历
     * <p>
     * 难度：easy
     * <p>
     * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
     * <p>
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     *
     * <p>
     * 请你返回有 最高得分 节点的 数目 。
     * <p>
     * 示例1
     * <p>
     * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * <p>
     * 输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
     * <p>
     * 范围
     * <p>
     * 节点总数在范围 [0, 104] 内
     * 0 <= Node.val <= 10^4
     * n 叉树的高度小于或等于 1000
     * <p>
     * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
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
    思路1：递归法
     */
    List<Integer> ans = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return ans;
        }
        dfs(root);
        return ans;
    }

    public void dfs(Node node) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < node.children.size(); i++) {
            dfs(node.children.get(i));
        }
        ans.add(node.val);
    }

    /*
    思路2：迭代法
     */
    public List<Integer> postorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        Deque<Node> deque1= new ArrayDeque();
        Deque<Integer> deque2= new ArrayDeque();
        if (root == null) {
            return res;
        }
        deque1.push(root);
        while (!deque1.isEmpty()) {
            Node node = deque1.pop();
            for (Node node1 : node.children) {
                deque1.push(node1);
            }
            deque2.push(node.val);
        }
        while (!deque2.isEmpty()) {
            res.add(deque2.pop());
        }

        return res;

    }
}



