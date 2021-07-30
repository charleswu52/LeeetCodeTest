package leetcode_hot100.top60;

/**
 * @author WuChao
 * @create 2021/7/17 14:57
 */
public class _206 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 206. 反转链表
     * 难度：easy
     * <p>
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * <p>
     * 示例：
     * 输入：head = [1,2]
     * 输出：[2,1]
     *
     * 输入：head = []
     * 输出：[]
     * <p>
     * 进阶：
     * 链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
     * <p>
     * 数据范围
     * 链表中节点的数目范围是 [0, 5000]
     * -5000 <= Node.val <= 5000
     */

    // 链表节点定义
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
    思路1：迭代
    翻转链表解决思路：
    使用双指针，记录一个当前的和之前的指针，循环即可

     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode node = cur.next;
            cur.next = pre;
            pre = cur;
            cur = node;

        }
        return pre;
    }

    /*
    思路2：递归

     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList2(head.next);

        head.next.next = head;
        // 注意这一点 否则链表容易成环
        head.next = null;
        return newHead;

    }
}