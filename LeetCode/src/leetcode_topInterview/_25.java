package leetcode_topInterview;

/**
 * @author WuChao
 * @create 2021/7/28 20:43
 */
public class _25 {
    /**
     * codeTop 热题 面试常考
     * <p>
     * 25. K 个一组翻转链表
     * 难度：hard
     * <p>
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     *
     * k 是一个正整数，它的值小于或等于链表的长度。
     *
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * 进阶：
     *
     * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     *
     *
     * <p>
     * 示例
     *
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[2,1,4,3,5]
     *
     * <p>
     * 范围
     * 列表中节点的数量在范围 sz 内
     * 1 <= sz <= 5000
     * 0 <= Node.val <= 1000
     * 1 <= k <= sz
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
    思路：模拟

     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode();
        hair.next = head;
        ListNode pre = hair;
        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于k
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }
        return hair.next;
    }

    /**
     * 翻转链表
     * @param head
     * @param tail
     * @return
     */
    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode cur = head;
        while (prev != tail) {
            ListNode nex = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nex;
        }
        return new ListNode[]{tail, head};

    }
}
