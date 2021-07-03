package leetcode_hot100.top20;

/**
 * @author WuChao
 * @create 2021/7/3 9:22
 */
public class _2 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 2. 两数相加
     * 难度：medium
     * <p>
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * <p>
     * 示例
     * <p>
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     *
     * <p>
     * 范围
     * 每个链表中的节点数在范围 [1, 100] 内
     * 0 <= Node.val <= 9
     * 题目数据保证列表表示的数字不含前导零
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = null;
        ListNode res = null;

        ListNode first = l1;
        ListNode second = l2;
        int flag = 0;
        while (first != null && second != null) {
            int sum = first.val + second.val + flag;
            int val = sum % 10;
            flag = sum / 10;
            ListNode node = new ListNode(val);
            if (res == null) {
                res = node;
            }
            if (pre == null) {
                pre = node;
            } else {
                pre.next = node;
                pre = node;
            }
            first = first.next;
            second = second.next;
        }
        while (first != null) {
            int sum = first.val + flag;
            int val = sum % 10;
            flag = sum / 10;
            ListNode node = new ListNode(val);
            pre.next = node;
            pre = node;
            first = first.next;
        }
        while (second != null) {
            int sum = second.val + flag;
            int val = sum % 10;
            flag = sum / 10;
            ListNode node = new ListNode(val);
            pre.next = node;
            pre = node;
            second = second.next;
        }
        if (flag != 0) {
            ListNode node = new ListNode(flag);
            pre.next = node;
        }
        return res;
    }



}
