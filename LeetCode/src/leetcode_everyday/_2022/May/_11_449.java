package leetcode_everyday._2022.May;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/5/11 18:17
 */
public class _11_449 {
    /**
     * 每日一题：2022/5/11
     * <p>
     * 449. 序列化和反序列化二叉搜索树
     * <p>
     * 难度：medium
     * <p>
     * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
     *
     * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
     *
     * 编码的字符串应尽可能紧凑。
     * <p>
     * 示例
     * <p>
     * 输入：root = [2,1,3]
     * <p>
     * 输出：[2,1,3]
     * <p>
     * 范围
     * <p>
     * 提示：
     * 树中节点数范围是 [0, 104]
     * 0 <= Node.val <= 104
     * 题目数据 保证 输入的树是一棵二叉搜索树。
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

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return null;
            }
            List<String> list = new ArrayList<>();
            preorderDFS1(root, list);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
                if (i != list.size() - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }

        private void preorderDFS1(TreeNode node, List<String> list) {
            if (node == null) {
                return;
            }
            list.add(String.valueOf(node.val));
            preorderDFS1(node.left, list);
            preorderDFS1(node.right, list);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null) {
                return null;
            }
            String[] split = data.split(",");
            return preorderDFS2(0, split.length - 1, split);
        }

        private TreeNode preorderDFS2(int left, int right, String[] strings) {
            if (left > right) {
                return null;
            }
            int j = left + 1, t = Integer.parseInt(strings[left]);
            TreeNode res = new TreeNode(t);
            while (j <= right && Integer.parseInt(strings[j]) <= t) {
                j++;
            }
            res.left = preorderDFS2(left + 1, j - 1, strings);
            res.right = preorderDFS2(j, right, strings);
            return res;
        }
    }

}

