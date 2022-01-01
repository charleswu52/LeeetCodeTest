package leetcode_everyday._2021.Sep;

/**
 * @author WuChao
 * @create 2021/9/2 9:14
 */
public class _2_Offer_22 {
    /**
     * 每日一题：2021/9/2
     * 剑指 Offer 22. 链表中倒数第k个节点
     * 难度：easy
     * <p>
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     *
     * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
     *
     * <p>
     * 示例 1：
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     *
     * 返回链表 4->5.
     *
     *
     * <p>
     * 注意:
     */

    /**
     * 链表节点定义
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /*
    思路：双指针
    快慢指针的思想。我们将第一个指针 fast 指向链表的第 k + 1 个节点，第二个指针 slow 指向链表的第一个节点，此时指针 fast 与 slow
    二者之间刚好间隔 k 个节点。此时两个指针同步向后走，当第一个指针 fast 走到链表的尾部空节点时，
    则此时 slow 指针刚好指向链表的倒数第k个节点。

    我们首先将 fast 指向链表的头节点，然后向后走 k 步，则此时 fast 指针刚好指向链表的第 k + 1 个节点。

    我们首先将 slow 指向链表的头节点，同时 slow 与 fast 同步向后走，当 fast 指针指向链表的尾部空节点时，则此时返回 slow 所指向的节点即可。
     */

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;



    }
}
