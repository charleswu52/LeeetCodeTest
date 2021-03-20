import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author WuChao
 * @since 2021/3/20 上午8:19
 */
public class _37 {
    /**
     * 剑指 Offer 37. 序列化二叉树
     * 难度: hard
     * <p>
     * 请实现两个函数，分别用来序列化和反序列化二叉树。
     *
     * <p>
     * 例如:
     * 你可以将以下二叉树：
     * <p>
     *     1
     *    / \
     *   2   3
     *      / \
     *     4   5
     * <p>
     * 序列化为 "[1,2,3,null,null,4,5]"
     *
     * <p>
     * 数据范围：
     */

    // 二叉树定义
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    // 实现序列化与反序列化的类
    class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            if (root == null) {
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (stringBuilder.toString().length() == 1) {
                        stringBuilder.append(node.val);
                    } else {
                        stringBuilder.append("," + node.val);
                    }
                    queue.add(node.left);
                    queue.add(node.right);
                } else {
                    if (stringBuilder.toString().length() == 1) {
                        stringBuilder.append("null");
                    } else {
                        stringBuilder.append(",null");
                    }
                }
            }
            stringBuilder.append("]");
            return stringBuilder.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals("[]")) {
                return null;
            }
            String[] vals = data.substring(1, data.length() - 1).split(",");
            TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int i = 1;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                // 找其左子树
                if (!vals[i].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(vals[i]));
                    queue.add(node.left);
                }
                i++;
                // 添加有子树
                if (!vals[i].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(vals[i]));
                    queue.add(node.right);
                }
                i++;
            }
            return root;
        }



    }
    // 测试函数
    @Test
    public void test(){
        String data = "[1,2,3,null,null,4,5,null,null,null,null]";//"[1,2,3,null,null,4,5]";
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        a.left = b;
        a.right = c;
        c.left = d;
        c.right = e;
        Codec codec = new Codec();
        System.out.println(codec.serialize(a));
        TreeNode node = codec.deserialize(data);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            if (node1 != null) {
                System.out.println(node1.val);
                if (node1.left != null) {
                    queue.add(node1.left);
                }
                if (node1.right != null) {
                    queue.add(node1.right);
                }
            }
        }

    }


}
