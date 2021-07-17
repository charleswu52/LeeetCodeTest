package leetcode_hot100.top60;

/**
 * @author WuChao
 * @create 2021/7/17 8:07
 */
public class _160 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 160. 相交链表
     * 难度：easy
     * <p>
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
     *
     * 题目数据 保证 整个链式结构中不存在环。
     *
     * 注意，函数返回结果后，链表必须 保持其原始结构 。
     *
     * <p>
     * 示例：
     *
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Intersected at '8'
     * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
     * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     *
     * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * 输出：Intersected at '2'
     * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
     * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
     *
     *
     * <p>
     * 数据范围:
     * listA 中节点数目为 m
     * listB 中节点数目为 n
     * 0 <= m, n <= 3 * 104
     * 1 <= Node.val <= 105
     * 0 <= skipA <= m
     * 0 <= skipB <= n
     * 如果 listA 和 listB 没有交点，intersectVal 为 0
     * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
     *
     * 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
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
    思路1：
    先把两个链表都走一遍，统计下每个链表的长度，再根据两者之间长度的差值，设定不同的起始指针分别遍历两个
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode nodeA = headA, nodeB = headB;
        int m = 0, n = 0;
        while (nodeA != null) {
            m++;
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            n++;
            nodeB = nodeB.next;
        }
        nodeA = headA;
        nodeB = headB;
        if (m > n) {
            int minus = m - n;
            while (minus-- > 0) {
                nodeA = nodeA.next;
            }
        } else if (n > m) {
            int minus = n - m;
            while (minus-- > 0) {
                nodeB = nodeB.next;
            }
        }
        while (nodeA != null && nodeB != null) {
            if (nodeA == nodeB) {
                return nodeA;
            }
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        return null;
    }

    /*
    思路2：双指针
    也是遍历的思路，但是不用像思路1那样麻烦，不用判断两个的长度
    而是用两个指针，起初指针都在每个链表的起始位置，如果不一样长一方走完后，指针移动到另一根链表头位置
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB ) {
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
        }
        return nodeA;
    }


    }
