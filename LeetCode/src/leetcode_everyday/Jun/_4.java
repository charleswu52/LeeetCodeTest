package leetcode_everyday.Jun;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/6/4 上午8:16
 */
public class _4 {
    /**
     * 每日一题：2021/6/4
     * 160. 相交链表
     * 难度: easy
     * <p>
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
     * <p>
     * 题目数据 保证 整个链式结构中不存在环。
     * <p>
     * 注意，函数返回结果后，链表必须 保持其原始结构 。
     * <p>
     * 示例:
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Intersected at '8'
     * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
     * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     *
     * <p>
     * 数据范围：
     * listA 中节点数目为 m
     * listB 中节点数目为 n
     * 0 <= m, n <= 3 * 104
     * 1 <= Node.val <= 105
     * 0 <= skipA <= m
     * 0 <= skipB <= n
     * 如果 listA 和 listB 没有交点，intersectVal 为 0
     * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /*
    思路1：遍历两个链表计数
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode ha = headA;
        ListNode hb = headB;
        int countA = 0;
        int countB = 0;
        while (ha != null) {
            countA++;
            ha = ha.next;
        }
        while (hb != null) {
            countB++;
            hb = hb.next;
        }
        if (countA > countB) {
            int t = countA - countB;
            ha = headA;
            while (t != 0) {
                ha = ha.next;
                t--;
            }
            hb = headB;

        } else if (countB > countA) {
            int t = countB - countA;
            hb = headB;
            while (t != 0) {
                hb = hb.next;
                t--;
            }
            ha = headA;
        } else {
            ha = headA;
            hb = headB;
        }
        while (ha != null && hb != null) {
            if (ha == hb) {
                return ha;
            } else {
                ha = ha.next;
                hb = hb.next;
            }
        }
        return null;
    }

    /*
    思路2： 使用Set保存一个链表的节点 然后遍历另一个链表寻找起来
    比思路1的执行时间慢
    Set 慢，List更慢
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        /*
        Set<ListNode> setA = new HashSet<>();
        while (headA != null) {
            setA.add(headA);
            headA = headA.next;
        }
        if (setA.size() == 0) {
            return null;
        }
        while (headB != null) {
            if (setA.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;

         */

        List<ListNode> list = new ArrayList<>();
        while (headA != null) {
            list.add(headA);
            headA = headA.next;
        }
        if (list.size() == 0) {
            return null;
        }
        while (headB != null) {
            if (list.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
    /*
    理想的思路：双指针
    各自设置指向每个链表的头指针，然后各自开始遍历，当相等的时候返回，否则如果遍历完一遍后没有找到就再移动到另一个链表的头指针上开始
    另一个也是，当如果两个链表有重合的节点则一定会在各自移动到对方链表后找到，否则
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA, pb = headB;
        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }
        return pa;

    }



    }
