package leetcode_everyday._2022.Jun;

import com.sun.webkit.event.WCMouseWheelEvent;

/**
 * @author WuChao
 * @create 2022/6/18 10:46
 */
public class _18_2_029 {
    /**
     * 每日一题：2022/6/18
     * <p>
     * 剑指 Offer II 029. 排序的循环链表
     * <p>
     * 难度：medium
     * <p>
     * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
     *
     * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
     *
     * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
     *
     * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
     * <p>
     * 示例
     * <p>
     * 输入：head = [3,4,1], insertVal = 2
     * 输出：[3,4,1,2]
     * 解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。
     * 新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3
     * <p>
     * 范围
     * <p>
     * 1 <= arr.length <= 10000
     * 0 <= arr[i] <= 9
     */

    // 节点定义
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        Node cur = null, next = null;
        Node realHead = null;
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }
        cur = head;
        next = head.next;
        while (cur.val <= next.val) {
            cur = cur.next;
            next = next.next;
            if (cur == head) {
                break;
            }
        }
        realHead = next;
        while (next.val < insertVal) {
            cur = next;
            next = next.next;
            if (next == realHead) {
                break;
            }
        }
        cur.next = new Node(insertVal);
        cur = cur.next;
        cur.next = next;
        return head;

    }
}
