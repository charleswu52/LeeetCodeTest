package leetcode_everyday._2022.Jun;

/**
 * @author WuChao
 * @create 2022/6/28 9:44
 */
public class _28_324 {
    /**
     * 每日一题：2022/6/28
     * <p>
     * 324. 摆动排序 II
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
     * <p>
     * 你可以假设所有输入数组都可以得到满足题目要求的结果。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [1,5,1,1,6,4]
     * <p>
     * 输出：[1,6,1,5,1,4]
     * <p>
     * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 5 * 104
     * 0 <= nums[i] <= 5000
     * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
     */

    /*
    思路： 三分选择
     */
    int n = -1;

    public void wiggleSort(int[] nums) {
        //找到中位数索引
        int midIndex = this.quickSelect(nums, 0, nums.length - 1);
        //找到中位数
        int mid = nums[midIndex];
        n = nums.length;
        //三分法
        for (int i = 0, j = 0, k = nums.length - 1; j <= k; ) {
            if (nums[V(j)] > mid) {
                swap(nums, V(j++), V(i++));
            } else if (nums[V(j)] < mid) {
                swap(nums, V(j), V(k--));
            } else {
                j++;
            }
        }
    }

    public int V(int i) {
        return (1 + 2 * (i)) % (n | 1);
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public int quickSelect(int[] nums, int left, int right) {
        int pivot = nums[left];
        int l = left;
        int r = right;
        while (l < r) {
            while (l < r && nums[r] >= pivot) {
                r--;
            }
            if (l < r) {
                nums[l++] = nums[r];
            }
            while (l < r && nums[l] <= pivot) {
                l++;
            }
            if (l < r) {
                nums[r--] = nums[l];
            }
        }
        nums[l] = pivot;
        if (l == nums.length / 2) {
            return l;
        } else if (l > nums.length / 2) {
            return this.quickSelect(nums, left, l - 1);
        } else {
            return this.quickSelect(nums, l + 1, right);
        }
    }

}
