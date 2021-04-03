import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/4/3 下午3:57
 */
public class MianShi_34 {
    /**
     * 面试题34. 二叉树中和为某一值的路径
     * 难度: medium
     * <p>
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
     * <p>
     * 例如：
     * 给定如下二叉树，以及目标和 target = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     * 返回:
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     * <p>
     * 数据范围：
     * 节点总数 <= 10000
     */

    // 二叉树定义
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    /*
    题目解析：
    题目简单易懂，一想到的思路就是使用DFS，过程中计算路径的和，进行剪枝回退
    可以直接使用模板来做
     */
    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();


    public List<List<Integer>> pathSum(TreeNode root, int target) {
        backtrack(root, target);
        return res;

    }

    void backtrack(TreeNode root, int sum) {
        if (root == null) { //到根节点时终止
            return;
        }
        path.add(root.val);
        sum -= root.val;
        if (sum == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<>(path));
        }
        backtrack(root.left,sum);
        backtrack(root.right, sum);
        path.removeLast();
    }

}
