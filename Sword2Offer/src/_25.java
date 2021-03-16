/**
 * @author WuChao
 * @since 2021/3/16 上午8:59
 */
public class _25 {
    /**
     * 剑指 Offer 25. 合并两个排序的链表
     * 难度: easy
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
     * <p>
     * 示例：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * <p>
     * 数据范围：
     * 0 <= 链表长度 <= 1000
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode res = null;
        ListNode pre1 = null;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                if (res == null) {
                    res = l1;
                }
                pre1 = l1;
                l1 = l1.next;
            } else {
                ListNode pre2 = l2;
                l2 = pre2.next;
                pre2.next = l1;
                if (pre1 != null) {
                    pre1.next = pre2;
                }
                pre1 = pre2;
                if (res == null) {
                    res = pre2;
                }
            }
        }
        if (l1 == null) {
            pre1.next = l2;
        }
        return res;

    }
}
