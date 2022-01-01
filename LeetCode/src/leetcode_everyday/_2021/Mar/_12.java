package leetcode_everyday._2021.Mar;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/3/12 上午7:40
 */
public class _12 {
    /**
     * 每日一题：2021/3/12
     * 331. 验证二叉树的前序序列化
     * 难度: medium
     * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。
     * 如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
     *
     * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
     * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
     * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
     * <p>
     * 示例:
     * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
     * 输出: true
     * <p>
     * 数据范围：
     */


    /**
     * 下面的解决方式只考虑了个位数 ,将字符中的数全变成个位
     */
    public boolean isValidSerialization(String preorder) {
        HashMap<Integer, Integer> store = new HashMap<>();

        if (preorder.length() < 2) {
            if (preorder.charAt(0) != '#') {
                return false;
            }
        }

        System.out.println(preorder);

        List<Integer> deleteIdx = new ArrayList<>();
        for (int i = 1; i < preorder.length(); i++) {
            if (preorder.charAt(i) != '#' && preorder.charAt(i - 1) != '#' && preorder.charAt(i) != ',' && preorder.charAt(i - 1) != ',') {
                deleteIdx.add(i);
            }
        }
        System.out.println(deleteIdx);

        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < preorder.length(); i++) {
            if (Collections.binarySearch(deleteIdx, i) < 0) {
                temp.append(preorder.charAt(i));
            }
        }
        preorder = temp.toString();

        System.out.println(preorder);


        preorder = preorder.replace(",", "");
        System.out.println(preorder);


        for (int i = 0; i < preorder.length(); i++) {
            if (preorder.charAt(i) != '#') {
                store.put(i, 2);
            } else {
                store.put(i, 0);
            }
        }
        if (store.size() > 1 && store.get(0) == 0) {
            return false;
        }
        for (int i = 0; i < preorder.length(); i++) {
            if (preorder.charAt(i) == '#') {
                int j;
                for (j = i - 1; j >= 0; j--) {
                    if (preorder.charAt(j) != '#') {
                        if (store.get(j) > 0) {
                            store.put(j, store.get(j) - 1);
                            break;
                        }
                    }
                }
                if (j < 0 && i != 0) {
                    return false;
                }
            }
        }
        for (int i = 0; i < preorder.length(); i++) {
            if (preorder.charAt(i) != '#') {
                int j;
                for (j = i - 1; j >= 0; j--) {
                    if (preorder.charAt(j) != '#') {
                        if (store.get(j) > 0) {
                            store.put(j, store.get(j) - 1);
                            break;
                        }
                    }
                }
                if (j < 0 && i != 0) {
                    return false;
                }
            }
        }
        for (int value : store.values()) {
            if (value > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 参考其他题解的方式
     * 使用栈
     * 定义一个概念，叫做槽位。一个槽位可以被看作「当前二叉树中正在等待被节点填充」的那些位置。
     * 二叉树的建立也伴随着槽位数量的变化。每当遇到一个节点时：
     * 如果遇到了空节点，则要消耗一个槽位；
     * 如果遇到了非空节点，则除了消耗一个槽位外，还要再补充两个槽位。
     * 此外，还需要将根节点作为特殊情况处理。
     * <p>
     * 使用栈来维护槽位的变化。栈中的每个元素，代表了对应节点处剩余槽位的数量，而栈顶元素就对应着下一步可用的槽位数量。
     * 当遇到空节点时，仅将栈顶元素减 1；当遇到非空节点时，将栈顶元素减 1 后，再向栈中压入一个 2。
     * 无论何时，如果栈顶元素变为 0，就立刻将栈顶弹出。
     * <p>
     * 遍历结束后，若栈为空，说明没有待填充的槽位，因此是一个合法序列；否则若栈不为空，则序列不合法。
     * 此外，在遍历的过程中，若槽位数量不足，则序列不合法。
     */

    public boolean isValidSerialization2(String preorder) {
        int n = preorder.length();
        int i = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        while (i < n) {
            if (stack.empty()) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                i++;
            } else {
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                stack.push(2);
            }
        }
        return stack.empty();
    }

    /**
     * 题解：计数方式
     * 接着栈的思路，如果把栈中元素看成一个整体，即所有剩余槽位的数量，也能维护槽位的变化。
     * 因此，我们可以只维护一个计数器，代表栈中所有元素之和，其余的操作逻辑均可以保持不变。
     */
    public boolean isValidSerialization3(String preorder) {
        int n = preorder.length();
        int i =0;
        int slots = 1;
        while (i < n) {
            if (slots == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                slots--;
                i++;
            }else {
                // 读取数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                slots = slots + 2 - 1;//添加数消耗一个槽，同时增加2个槽
            }
        }
        return slots == 0;
    }



        public void _21_3_12() {
        String preorder = "1,#,#,#,#";
        System.out.println(isValidSerialization(preorder));
        System.out.println(isValidSerialization2(preorder));
        System.out.println(isValidSerialization3(preorder));
    }


}
