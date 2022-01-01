package leetcode_everyday._2021.Mar;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author WuChao
 * @since 2021/3/26 上午7:24
 */
public class _26 {
    /**
     * 每日一题：2021/3/26
     * 83. 删除排序链表中的重复元素
     * 难度: easy
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
     * 返回同样按升序排列的结果链表。
     * <p>
     * 示例：
     * 输入：head = [1,1,2]
     * 输出：[1,2]
     *
     *
     * <p>
     * 数据范围：
     * 链表中节点数目在范围 [0, 300] 内
     * -100 <= Node.val <= 100
     * 题目数据保证链表已经按升序排列
     */

    /*
     题目分析: 与昨天的题目类似，属于只不过这里对重复元素要求保留一个
     还是按照昨天的两种做法，第一种使用TreeMap存储节点信息，第二种就是使用指针一次遍历改变next下标
     */

    /**
     * 节点的定义
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

    /**
     * 第一种做法，使用TreeMap存储下来
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        TreeMap<Integer, ListNode> store = new TreeMap<>();
        ListNode node = head;
        while (node != null) {
            if (!store.containsKey(node.val)) {
                store.put(node.val, node);
            }
            node = node.next;
        }
        int count = 0;
        ListNode newHead = null, pre = null;
        for (Map.Entry<Integer, ListNode> entry : store.entrySet()) {
            if (count == 0) {
                newHead = entry.getValue();
                pre = entry.getValue();
            } else {
                pre.next = entry.getValue();
                pre = entry.getValue();
            }
            count++;
        }
        pre.next = null;
        return newHead;
    }


    /**
     * 第二种做法，遍历指针的形式
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head,pre = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                int x = cur.val;
                while (cur != null && cur.val == x) {
                    cur = cur.next;
                }
                pre.next = cur;
                pre = cur;
            } else {
                cur = cur.next;
                pre = cur;
            }
        }
        return head;

    }

}
