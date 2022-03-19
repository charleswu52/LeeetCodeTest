package leetcode_everyday._2022.Mar;

/**
 * @author WuChao
 * @create 2022/3/19 上午9:28
 */
public class _19_606 {
    /**
     * 每日一题：2022/3/19
     * <p>
     * 606. 根据二叉树创建字符串
     * <p>
     * 难度：easy
     * <p>
     * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
     * <p>
     * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
     * <p>
     * 示例1
     * <p>
     * 输入: 二叉树: [1,2,3,4]
     * 1
     * /   \
     * 2     3
     * /
     * 4
     * <p>
     * 输出: "1(2(4))(3)"
     * <p>
     * 解释: 原本将是“1(2(4)())(3())”，
     * 在你省略所有不必要的空括号对之后，
     * 它将是“1(2(4))(3)”。
     * <p>
     * 范围
     * <p>
     * n == balance.length
     * 1 <= n, account, account1, account2 <= 105
     * 0 <= balance[i], money <= 1012
     * transfer, deposit, withdraw 三个函数，每个 最多调用 10^4 次
     */
    /**
     * 二叉树节点定义
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

    StringBuilder stringBuilder = new StringBuilder();

    public String tree2str(TreeNode root) {
        dfs2(root);
        return stringBuilder.toString().substring(1,stringBuilder.length()-1);

    }

    public void dfs(TreeNode node) {
        if (node != null) {
            stringBuilder.append(node.val);
            if (node.left != null || node.right != null) {
                stringBuilder.append('(');
                dfs(node.left);
                stringBuilder.append(')');
                if (node.right != null) {
                    stringBuilder.append('(');
                    dfs(node.right);
                    stringBuilder.append(')');
                }
            }
        }
    }

    public void dfs2(TreeNode node) {
        stringBuilder.append('(');
        stringBuilder.append(node.val);
        if (node.left != null) {
            dfs2(node.left);
        } else if (node.right != null) {
            stringBuilder.append("()");
        }
        if (node.right != null) {
            dfs2(node.right);
        }
        stringBuilder.append(')');
    }
}
