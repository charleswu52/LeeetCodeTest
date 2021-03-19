import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/19 上午10:44
 */
public class _36 {
    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表
     * 难度: medium
     * <p>
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。Node
     *
     * <p>
     * 例如:
     * 给定二叉搜索树:
     * 5
     * / \
     * 2   6
     * / \
     * 1   3
     * 示例：
     * 输入: [1,6,3,2,5]
     * 输出: false
     * <p>
     * 输入: [1,3,2,6,5]
     * 输出: true
     * ]
     * <p>
     * 数据范围：
     * 数组长度 <= 1000
     */

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    List<Integer> store;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        store = new LinkedList<>();
        inOrder(root);
        int[] arr = store.stream().mapToInt(Integer::intValue).toArray();

        Node head = new Node(arr[0]);
        Node pre = head;
        for (int i = 1; i < arr.length; i++) {
            Node node = new Node(arr[i]);
            node.left = pre;
            pre.right = node;
            pre = node;
        }
        pre.right = head;
        head.left = pre;
        return head;



    }

    public void inOrder(Node node){
        if (node != null) {
            inOrder(node.left);
            store.add(node.val);
            inOrder(node.right);
        }
    }



}
