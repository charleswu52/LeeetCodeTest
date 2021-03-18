package leetcodetest.Mar;

/**
 * @author WuChao
 * @since 2021/3/18 上午7:54
 */
public class _18 {
    /**
     * 每日一题：2021/3/18
     * 92. 反转链表 II
     * 难度: medium
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     * <p>
     * 示例:
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     * <p>
     * 数据范围：
     */

    // 链表定义
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

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 针对头节点可能会发生变化，因此定义一个虚拟的头节点
        ListNode preHead = new ListNode(-1);
        preHead.next = head;

        ListNode pre = preHead;
        //第一步：让虚拟头节点走到left节点的前一个节点
        for (int i = 0; i < left-1; i++) {
            pre = pre.next;
        }
        //第二步：从pre再走到right-left+1步，走到right节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        // 第三步：截出要反转的子链表
        ListNode leftNode = pre.next;
        ListNode cur = rightNode.next;
        // 切断连接
        pre.next = null;
        rightNode.next = null;

        // 第四步：反转子链表
        reverseLinkedList(leftNode);

        // 第五步：接回原来的链表上
        pre.next = rightNode;
        leftNode.next = cur;
        return preHead.next;
    }

    public void reverseLinkedList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }
}
