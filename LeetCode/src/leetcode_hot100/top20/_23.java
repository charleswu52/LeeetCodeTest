package leetcode_hot100.top20;

/**
 * @author WuChao
 * @create 2021/7/5 11:40
 */
public class _23 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 23. 合并K个升序链表
     * 难度：hard
     * <p>
     * 给你一个链表数组，每个链表都已经按升序排列。
     * <p>
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * <p>
     * 示例
     * <p>
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     *
     *
     * <p>
     * 输入：n = 1
     * 输出：["()"]
     * <p>
     * 范围
     * 1 <= n <= 8
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

    /*
    合并两个有序链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode pre = null;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                if (res.next == null) {
                    res.next = l1;
                }
                if (pre != null) {
                    pre.next = l1;
                }
                pre = l1;
                l1 = l1.next;
            } else {
                if (res.next == null) {
                    res.next = l2;
                }
                if (pre != null) {
                    pre.next = l2;
                }
                pre = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            if (res.next == null) {
                res.next = l1;
            } else if (pre != null) {
                pre.next = l1;
            }
        }
        if (l2 != null) {
            if (res.next == null) {
                res.next = l2;
            } else if (pre != null) {
                pre.next = l2;
            }
        }
        return res.next;
    }

    /*
    合并 k 个 有序链表
    思路：顺序合并，调用合并两个有序链表的方法，两两合并
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; i++) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }
}
