package leetcode_hot100.top80;

/**
 * @author WuChao
 * @create 2021/7/20 8:58
 */
public class _234 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 234. 回文链表
     * 难度：easy
     * <p>
     * 请判断一个链表是否为回文链表。
     *
     * <p>
     * 示例 1:
     *
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     *
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
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
    题目解析：
    题目简单明确，要求时间复杂度O(n),空间复杂度 O(1)

    思路1：翻转后半部分,然后使用双指针进行判断
        1.划分链表,将链表划分为两部分
        2.反转后半部分链表
        3.前后半部分进行比较，得出结论
        4.恢复链表并返回答案
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到链表中间位置，并划分
        ListNode halfNode = halfList(head);
        ListNode reverseNode = reverseList(halfNode.next);

        // 开始判断
        ListNode p1 = head, p2 = reverseNode;
        boolean res = true;
        while (res && p2 != null) {
            if (p1.val != p2.val) {
                res = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 将链表恢复 可选操作
        halfNode.next = reverseList(reverseNode);
        return res;


    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur =  head;
        while (cur != null) {
            ListNode node = cur.next;
            cur.next = pre;
            pre = cur;
            cur = node;
        }
        return pre;
    }

    /**
     * 找到链表中间位置
     * @param head
     * @return
     */
    public ListNode halfList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;

    }
}
