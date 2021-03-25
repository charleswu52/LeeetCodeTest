package leetcodetest.Mar;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/3/25 上午7:51
 */
public class _25 {
    /**
     * 每日一题：2021/3/25
     * 82. 删除排序链表中的重复元素 II
     * 难度: medium
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     * <p>
     * 示例：
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     *
     *
     * <p>
     * 数据范围：
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

    /*
    使用treeMap对值对应的节点进行存储
    对map进行遍历存，并删除有重复值的节点
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 先使用 TreeMap 的方式存储排序链表
        TreeMap<Integer, List<ListNode>> store = new TreeMap<>();

        if (head == null) {
            return head;
        }
        ListNode node = head;
        while (node != null) {
            if (!store.containsKey(node.val)) {
                store.put(node.val, new ArrayList<>());
            }
            store.get(node.val).add(node);
            node = node.next;
        }


        //遍历HashMap 并判断重复元素删除
        store.entrySet().removeIf(entry -> entry.getValue().size() > 1); // lambda 表达式的方法
        /*
        普通做法
        Iterator<Map.Entry<Integer, List<ListNode>>> iterator = store.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<ListNode>> entry = iterator.next();
            if (entry.getValue().size() > 1) {
                iterator.remove();
            }
        }
         */

        if (store.size() < 1) {
            return null;
        }
        int count  = 0;
        ListNode newHead = null, pre = null;
        for (Map.Entry<Integer, List<ListNode>> entry : store.entrySet()) {
            if (count == 0) {
                newHead = entry.getValue().get(0);
                pre = entry.getValue().get(0);
            } else {
                pre.next = entry.getValue().get(0);
                pre = entry.getValue().get(0);
            }
            count++;
        }
        pre.next = null;
        return newHead;
    }

    /*
    正常一次遍历的思路：主要也是题目想考察的方式
    在遍历过程中，头结点可能会被删除，因此需要一个哑节点指向链表的头结点
    从指针cur指向链表的哑节点，随后对链表进行遍历。如果当前cur.next 与 cur.next.next对应的元素相同，
    那么就需要将cur.next以及所有后面有相同元素值的链表节点全部删除。
    ！！ 记下这个元素值x ！！，随后不断将cur.next从链表中移除，直到cur.next为空节点或者其元素值不等于x为止。
    此时，将链表中所有元素值为x的节点已经全部删除。
    如果cur.next 与 cur.next.next对应的元素不相同，那么说明链表中只有一个元素值为cur.next节点，那么就可以将cur指向cur.next
    遍历完成整个链表之后，返回链表的哑节点的下一个节点即可

     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head); // 指向头结点的哑节点
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;// 先保存下重复元素的值
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }


        @Test
    public void test() {
        ListNode a = new ListNode(-3);
        ListNode b = new ListNode(-1);
        ListNode c = new ListNode(0);
        ListNode d = new ListNode(0);
        ListNode e = new ListNode(0);
        ListNode f = new ListNode(3);
        ListNode g = new ListNode(3);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        ListNode res = deleteDuplicates(a);
        ListNode res2 = deleteDuplicates2(a);
        while (res2 != null) {
            System.out.println(res2.val);
            res2 = res2.next;
        }

    }

}
