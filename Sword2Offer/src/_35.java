/**
 * @author WuChao
 * @since 2021/3/19 上午9:27
 */
public class _35 {
    /**
     * 剑指 Offer 35. 复杂链表的复制
     * 难度: medium
     * <p>
     * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
     * 还有一个 random 指针指向链表中的任意节点或者 null。
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
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node node = head;
        // 在原有节点后复制一个值相同的节点
        while (node != null) {
            Node cloneNode = new Node(node.val);
            cloneNode.next = node.next;
            node.next = cloneNode;
            node = cloneNode.next;
        }
        // 完成随机指针的复制
        node = head;
        Node cloneNode = head.next;
        while (node != null) {
            if (node.random != null) {
                node.next.random = node.random.next; // 将复制节点的随机节点指向当前节点的随机节点的下一个（复制的）
            }
            node = node.next.next; // 因为每一个后面都跟着一个复制的节点，所以一次要向后移动两个位置
        }
        //将链表进行拆分,将复制的后个节点从元链表中剥离开
        node = head;
        Node cloneHead = head.next;
        cloneNode = cloneHead;
        while (node != null) {
            node.next = cloneNode.next;
            node = node.next;
            if (cloneNode.next != null) {
                cloneNode.next = cloneNode.next.next;
                cloneNode = cloneNode.next;
            }
        }
        return cloneHead;
    }
}
