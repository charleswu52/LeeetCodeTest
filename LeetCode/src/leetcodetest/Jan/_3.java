package leetcodetest.Jan;


public class _3 {
    /**
     * 每日一题：2021/1/3
     * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
     * 你应当保留两个分区中每个节点的初始相对位置。
     *
     * 示例：
     * 输入：head = 1->4->3->2->5->2, x = 3
     * 输出：1->2->2->4->3->5
     */

    /**
     * 链表的定义
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;

    }

    public static void _21_1_3() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(2);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(2);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        ListNode res = partition(a, 3);
        while (res != null) {
            if (res.next != null) {
                System.out.print(res.val + "->");

            } else {
                System.out.print(res.val);

            }
            res = res.next;
        }
        System.out.println();

    }
}
