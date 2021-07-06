package leetcode_hot100.top20;

/**
 * @author WuChao
 * @create 2021/7/5 10:08
 */
public class _19 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 19. 删除链表的倒数第 N 个结点
     * 难度：medium
     * <p>
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * <p>
     * 进阶：你能尝试使用一趟扫描实现吗？
     * <p>
     * 示例
     * <p>
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     * <p>
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     * <p>
     * 范围
     * 链表中结点的数目为 sz
     * 1 <= sz <= 30
     * 0 <= Node.val <= 100
     * 1 <= n <= sz
     */

    /*
    链表节点定义
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        int count = 0;
        while (first != null) {
            count++;
            first = first.next;
        }
        int minus = count - n;
        if (minus == 0) {
            return head.next;
        }
        first = head;
        count = 1;
        while (first != null && count < minus) {
            count++;
            first = first.next;
        }
        first.next = first.next.next;
        return head;

    }
}
