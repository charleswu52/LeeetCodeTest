package chp2;

/**
 * @author WuChao
 * @since 2021/4/8 上午10:28
 */
public class _4 {
    /**
     * 程序员面试金典(version 6) - 面试题 02.04. 分割链表
     * 难度: easy
     * <p>
     * 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。
     * 如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
     * <p>
     * 例如：
     * 示例:
     * 输入: head = 3->5->8->5->10->2->1, x = 5
     * 输出: 3->1->2->10->5->5->8
     *
     * <p>
     * 数据范围：
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /*
    思路：遍历，模拟
    难点：头结点（哑节点）的设置
    之前想的复杂了，其实直接将链表中小于x的和大于等于x的节点分两个链表即可
     */

    public ListNode partition(ListNode head, int x) {
        // 将原链表分成两个子链表
        // 两个子链表的头节点各设置一个哑节点
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

}
