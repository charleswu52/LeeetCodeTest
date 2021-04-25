package chp4;


/**
 * @author WuChao
 * @since 2021/4/25 上午8:53
 */
public class _2 {
    /**
     * 程序员面试金典(version 6) - 面试题 04.02. 最小高度树
     * 难度: easy
     * <p>
     * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
     * <p>
     * 例如：
     * 给定有序数组: [-10,-3,0,5,9],
     * <p>
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     * <p>
     * 0
     * / \
     * -3   9
     * /   /
     * -10  5
     *
     * <p>
     * 数据范围：
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return null;
        }
        return BST(nums, 0, n-1);
    }


    /**
     * 递归构建二叉搜索树
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public TreeNode BST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode temp = new TreeNode(nums[mid]);
        temp.left = BST(nums, left, mid - 1);
        temp.right = BST(nums, mid + 1, right);
        return temp;
    }
}
