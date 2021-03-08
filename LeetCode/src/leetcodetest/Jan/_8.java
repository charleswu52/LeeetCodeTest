package leetcodetest.Jan;

public class _8 {
    /**
     * 每日一题：2021/1/8
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     */

    /**
     * 思路1：比较暴力的算法
     * 将数组中的后k个数与其前面的数挨个交换，把他们换到前k个数
     *
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        if (k == 0) {
            return;
        }
        for (int i = len - k, l = 0; i < len; i++, l++) {
            for (int j = i; j > l; j--) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
            }
        }

    }

    /** 思路2：对原数组做翻转
     * 首先将整个数组翻转，这样后半段元素已经变到前面了，只是后半段元素的位置是反的，
     * 然后还需要将翻转后的数组按照[0,k]和[k+1,n]翻转一次，这样就可以得到结果
     * 步骤：
     * 1. 将[0,n-1]数组长度整体翻转
     * 2. 将[0,k]长度翻转
     * 3. 将[k+1,n-1]长度翻转
     * @param nums
     * @param k
     */
    public static void rotate2(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        if (k == 0) {
            return;
        }
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    public static void reverse(int[] nums, int l, int r) {
        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }


    public static void _21_1_8() {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        rotate2(nums, k);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

}
