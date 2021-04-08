package chp2;

/**
 * @author WuChao
 * @since 2021/4/8 上午9:27
 */
public class _2 {
    /**
     * 程序员面试金典(version 6) - 面试题 02.02. 返回倒数第 k 个节点
     * 难度: easy
     * <p>
     * <p>
     * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
     * <p>
     * 注意：本题相对原题稍作改动
     * <p>
     * 例如：
     * 输入： 1->2->3->4->5 和 k = 2
     * 输出： 4
     *
     * <p>
     * 数据范围：
     * 链表长度在[0, 20000]范围内。
     * 链表元素在[0, 20000]范围内。
     * <p>
     * 如果不得使用临时缓冲区，该怎么解决？
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /*
    解法1：两次遍历，第一次遍历统计链表节点的数量，第二次从头再遍历到目标节点
    时间复杂度 O(n)
    空间复杂度 O(1)
     */
    public int kthToLast(ListNode head, int k) {
        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        node = head;
        while (count > k) {
            count--;
            node = node.next;
        }
        return node.val;
    }

    /*
    解法2：双指针
    第一个指针先移动k步，第二个指针从开头开始两个一起移动，当第一个指针到达结尾的时候，停止，返回第二个指针指向的元素
     */
    public int kthToLast2(ListNode head, int k) {
        ListNode fast = head, low = head;
        while (fast != null && k > 0) {
            k--;
            fast = fast.next;
        }
        while (fast != null && low != null) {
            fast = fast.next;
            low = low.next;
        }
        return low.val;
    }





    }
