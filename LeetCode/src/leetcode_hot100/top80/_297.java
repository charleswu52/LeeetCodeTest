package leetcode_hot100.top80;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/21 13:30
 */
public class _297 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 297. 二叉树的序列化与反序列化
     * 难度：hard
     * <p>
     * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
     * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
     *
     * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
     * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     *
     * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
     * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
     *
     * <p>
     * 示例：
     * 输入：root = [1,2,3,null,null,4,5]
     * 输出：[1,2,3,null,null,4,5]
     *
     * 输入：root = [1]
     * 输出：[1]
     *
     * 输入：root = [1,2]
     * 输出：[1,2]
     * <p>
     * 数据范围:
     *树中结点数在范围 [0, 104] 内
     * -1000 <= Node.val <= 1000
     */

    // 二叉树
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
    /*
    题目解析：二叉树序列化与反序列化

    思路：深度优先遍历
     */

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return serializeHelper(root, "");

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] split = data.split(",");
            List<String> dataList = new LinkedList<>(Arrays.asList(split));
            return deserilizeHelper(dataList);
        }

        /**
         * 深度优先遍历 进行二叉树的序列化
         * @param node
         * @param res
         * @return
         */
        public String serializeHelper(TreeNode node, String res) {
            if (node == null) {
                return res + "null,";
            } else {
                res += node.val + ",";
                res = serializeHelper(node.left, res);
                res = serializeHelper(node.right, res);
            }
            return res;
        }

        public TreeNode deserilizeHelper(List<String> dataList) {
            if (dataList.get(0).endsWith("null")) {
                dataList.remove(0);
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
            dataList.remove(0);
            root.left = deserilizeHelper(dataList);
            root.right = deserilizeHelper(dataList);
            return root;
        }


    }
}
