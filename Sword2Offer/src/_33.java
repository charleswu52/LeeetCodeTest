import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/19 上午8:27
 */
public class _33 {
    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     * 难度: medium
     * <p>
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同
     * <p>
     * 例如:
     * 给定二叉搜索树:
     * 5
     * / \
     * 2   6
     * / \
     * 1   3
     * 示例：
     * 输入: [1,6,3,2,5]
     * 输出: false
     * <p>
     * 输入: [1,3,2,6,5]
     * 输出: true
     * ]
     * <p>
     * 数据范围：
     * 数组长度 <= 1000
     */

    public boolean verifyPostorder(int[] postorder) {
        int len = postorder.length;
        if (len <= 1) {
            return true;
        }
        return recur(postorder, 0, len - 1);


    }

    /**
     * 递归分治
     * @param postorder
     * @param i         区间左端
     * @param j         区间右端,也是根节点对应的下标
     * @return
     */
    public boolean recur(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        int p = i; // 搜索第一个大于根节点的下标
        while (postorder[p] < postorder[j]) {
            p++;
        }
        int m = p;
        // 判断右子树是否合法
        while (postorder[p] > postorder[j]) {
            p++;
        }
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    @Test
    public void test() {
        int[] postorder = {1, 3, 2, 6, 5};
        System.out.println(verifyPostorder(postorder));
    }
}
