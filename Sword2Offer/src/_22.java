

/**
 * @author WuChao
 * @since 2021/3/16 上午8:00
 */
public class _22 {
    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     * 难度: easy
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * <p>
     * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
     *
     * <p>
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     *
     * 返回链表 4->5.
     * <p>
     * 数据范围：
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 快慢指针的方式
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode low = head, fast = head;
        while (k > 1 && fast != null) {
            fast = fast.next;
            k--;
        }
        if (fast == null) {
            return null;
        }
        while (fast.next != null) {
            low = low.next;
            fast = fast.next;
        }
        return low;


    }

}
