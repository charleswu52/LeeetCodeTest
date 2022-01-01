package leetcode_everyday._2021.Nov;

/**
 * @author WuChao
 * @create 2021/11/2 9:01
 */
public class _2_237 {
    /**
     * 每日一题：2021/11/2
     * <p>
     * 237. 删除链表中的节点
     * <p>
     * 难度：easy
     * <p>
     * 请编写一个函数，用于 删除单链表中某个特定节点 。在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
     *
     * 题目数据保证需要删除的节点 不是末尾节点 。
     *
     * <p>
     * 示例1：
     * <p>
     * 输入：head = [4,5,1,9], node = 5
     *
     * 输出：[4,1,9]
     *
     * 解释：指定链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9
     *
     * 范围
     * <p>
     * 链表中节点的数目范围是 [2, 1000]
     * -1000 <= Node.val <= 1000
     * 链表中每个节点的值都是唯一的
     * 需要删除的节点 node 是 链表中的一个有效节点 ，且 不是末尾节点
     */

    /**
     * 链表节点定义
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
    思路：比较无聊的题目 考验读题的能力
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;

    }


}
