package leetcode_everyday.Jul;

/**
 * @author WuChao
 * @create 2021/7/21 8:04
 */
public class _21 {
    /**
     * <p> 每日一题：2021/7/21</p>
     * <p>剑指 Offer 52. 两个链表的第一个公共节点</p>
     * <p>难度: easy</p>
     * <p>
     *     输入两个链表，找出它们的第一个公共节点。
     * </p>
     * <p>示例</p>
     * <p>
     *     输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * </p>
     * <p>
     *     输出：Reference of the node with value = 8
     * </p>
     * <p>
     * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，
     * 链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     * </p>
     *
     * <p>范围</p>
     * <p>
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     * </p>
     *
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
    思路：双指针
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
        }
        return nodeA;

    }
}
