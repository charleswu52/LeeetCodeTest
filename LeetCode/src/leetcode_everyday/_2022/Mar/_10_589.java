package leetcode_everyday._2022.Mar;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/3/10 8:52
 */
public class _10_589 {
    /**
     * 每日一题：2022/3/10
     * <p>
     * 589. N 叉树的前序遍历
     * <p>
     * 难度：easy
     * <p>
     * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
     * <p>
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     *
     * <p>
     * 示例1
     * <p>
     * 输入：root = [1,null,3,2,4,null,5,6]
     * <p>
     * 输出：[1,3,5,6,2,4]
     * <p>
     * 示例1
     * <p>
     * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * <p>
     * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
     * <p>
     * 范围
     * <p>
     * 节点总数在范围 [0, 10^4]内
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

    ;

    /*
    思路1:迭代法

     */
    public List<Integer> preorder1(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            Node node = queue.pop();
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--) {
                queue.push(node.children.get(i));
            }
        }
        return res;
    }

    /*
    思路2:递归法

     */
    List<Integer> ans = new ArrayList<>();
    public List<Integer> preorder2(Node root) {
        dfs(root);
        return ans;
    }
    public void dfs(Node root){
        if (root == null) {
            return;
        }
        ans.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            dfs(root.children.get(i));
        }
    }

}
