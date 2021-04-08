package chp2;

/**
 * @author WuChao
 * @since 2021/4/8 上午11:41
 */
public class _5 {
    /**
     * 程序员面试金典(version 6) - 面试题 02.05. 链表求和
     * 难度: easy
     * <p>
     * 给定两个用链表表示的整数，每个节点包含一个数位。
     * <p>
     * 这些数位是反向存放的，也就是个位排在链表首部。
     * <p>
     * 编写函数对这两个整数求和，并用链表形式返回结果。
     * <p>
     * 例如：
     * 示例:
     * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
     * 输出：2 -> 1 -> 9，即912
     * <p>
     * 进阶：思考一下，假设这些数位是正向存放的，又该如何解决呢?
     * <p>
     * 示例：
     * <p>
     * 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
     * 输出：9 -> 1 -> 2，即912
     *
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
    思路1 : 朴素的实现方式，将链表节点元素转换为int，然后相加得到结果
    缺点：未考虑到大数越界问题，非正解。
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int len1 = getLength(l1);
        if (len1 == -1) {
            return l2;
        }
        int len2 = getLength(l2);
        if (len2 == -1) {
            return l1;
        }
        long sum = list2Int(l1, len1) + list2Int(l2, len2);

        ListNode node = l1, pre = l1;
        while (node != null && sum != 0) {
            int temp = (int) (sum % 10);
            node.val = temp;
            pre = node;
            node = node.next;
            sum /= 10;
        }
        if (sum == 0) {
            if (node == null) {
                return l1;
            } else {
                pre.next = null;
            }
        } else {
            while (sum != 0) {
                ListNode newNode = new ListNode((int) (sum % 10));
                pre.next = newNode;
                pre = newNode;
                sum /= 10;
            }
            pre.next = null;
        }
        return l1;


    }

    public int getLength(ListNode listNode) {
        int count = -1;
        ListNode node = listNode;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    public int list2Int(ListNode listNode, int count) {
        ListNode node = listNode;
        int ans = 0;
        for (int i = 0; i <= count; i++) {
            ans += node.val * Math.pow(10, i);
            node = node.next;
        }
        return ans;
    }


    /*
    正解：遍历两个链表，逐位相加，同时考虑进位
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 判空输
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        // 遍历两个输链表
        ListNode first = null;
        ListNode head = null;
        int tmp = 0;
        while (l1 != null || l2 != null) {
            tmp = tmp + (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);
            if (first == null) {
                first = new ListNode(tmp % 10);
                head = first;

            } else {
                first.next = new ListNode(tmp % 10);
                first = first.next;
            }
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            tmp = tmp / 10;
        }
        // 处理相加的进位
        if (tmp != 0) {
            first.next = new ListNode(tmp);
        }
        return head;

    }
}