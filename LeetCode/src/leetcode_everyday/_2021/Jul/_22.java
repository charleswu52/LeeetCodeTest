package leetcode_everyday._2021.Jul;

/**
 * @author WuChao
 * @create 2021/7/22 上午8:37
 */
public class _22 {
    /**
     * <p> 每日一题：2021/7/22</p>
     * <p>138. 复制带随机指针的链表</p>
     * <p>难度: medium</p>
     * <p>
     * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
     * <p>
     * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
     * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
     * 复制链表中的指针都不应指向原链表中的节点 。
     * <p>
     * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
     * <p>
     * 返回复制链表的头节点。
     * <p>
     * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
     * <p>
     * val：一个表示 Node.val 的整数。
     * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
     * 你的代码 只 接受原链表的头节点 head 作为传入参数。
     * </p>
     * <p>示例</p>
     * <p>
     * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
     *
     * </p>
     *
     * <p>范围</p>
     * <p>
     * 0 <= n <= 1000
     * -10000 <= Node.val <= 10000
     * Node.random 为空（null）或指向链表中的节点。
     * </p>
     */

    // 节点定义
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

    /*
    迭代  +  节点拆分
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }

        Node node = head;
        // 先在每个节点的后面都复制一个节点
        while (node != null) {
            Node clone = new Node(node.val);
            clone.next = node.next;
            node.next = clone;
            node = clone.next;
        }

        // 在对添加的每个节点复制  random 指针
        node = head;
        while (node != null) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }

        // 切分链表，将复制后的节点单独形成一个新的链表
        node = head;
        Node cloneHead = node.next;
        Node clone = node.next;
        while (node != null) {
            node.next = clone.next;
            node = node.next;
            if (clone.next != null) {
                clone.next = clone.next.next;
                clone = clone.next;
            }
        }
        return cloneHead;

    }

}
