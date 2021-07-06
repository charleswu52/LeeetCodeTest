package leetcode_hot100.top20;

/**
 * @author WuChao
 * @create 2021/7/5 10:47
 */
public class _21 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 21. 合并两个有序链表
     * 难度：easy
     * <p>
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * <p>
     * 示例
     * <p>
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     * <p>
     * 输入：l1 = [], l2 = []
     * 输出：[]
     * <p>
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     * <p>
     * 范围
     * 两个链表的节点数目范围是 [0, 50]
     * -100 <= Node.val <= 100
     * l1 和 l2 均按 非递减顺序 排列
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
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

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
}
