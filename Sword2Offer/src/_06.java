import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/3/10 上午10:16
 */
public class _06 {
    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * 难度: easy
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * <p>
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     * <p>
     * 数据范围：
     * 0 <= 链表长度 <= 10000
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * List添加 逆序遍历
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        List<Integer> store = new ArrayList<>();
        while (head != null) {
            store.add(head.val);
            head = head.next;
        }
        System.out.println(store);
        int[] res = new int[store.size()];
        for (int j = 0, i = store.size() - 1; i >= 0; j++, i--) {
            res[j] = store.get(i);
        }
        return res;

    }

    /**
     * 使用栈
     * @param head
     * @return
     */
    public int[] reversePrint2(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int[] res = new int[stack.size()];
        int i = 0;
        while (!stack.empty()) {
            res[i++] = stack.pop();
        }
        return res;
    }


    public void sword2Offer_06() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(2);
        a.next = b;
        b.next = c;
        System.out.println(Arrays.toString(reversePrint(a)));
        System.out.println(Arrays.toString(reversePrint2(a)));


    }
}
