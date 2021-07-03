package leetcode_everyday.Apr;

/**
 * @author WuChao
 * @since 2021/4/7 上午8:22
 */
public class _7_2 {
    /**
     * 每日一题：2021/4/7
     * 33. 搜索旋转排序数组
     * 难度: medium
     * <p>
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
     * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
     * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * <p>
     * 示例：
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 5000
     * -10^4 <= nums[i] <= 10^4
     * nums 中的每个值都 独一无二
     * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
     * -10^4 <= target <= 10^4
     */

    /*
    题目解析：
    简单的，数组元素搜索问题，首先题目告知，数组元素按升序排列，且元素值互不相同，因此考虑是否能用二分，因为二分查找效率是最高的
    刚看题目的第一眼，想到的就是使用二分，但是由于数组并不是全部的升序数组是经过旋转后的，因此在想难道是将其还原，或者开辟一个新数组，然后在
    新数组得到原数组的升序子序列，可是这样的话时间空间复杂度都不低，在还没进行搜索之前时间复杂度就达到了O(n),这样做的话还不如直接遍历搜索，
    显然不是最优解。
    因此，除了朴素的暴力搜索之外，题目的真正考察点就是如何对旋转后的数组使用二分查找。
     */
    /*
    对旋转后的升序数组使用二分查找的思路：
    将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。
    拿示例来看，我们从 6 这个位置分开以后数组变成了 [4, 5, 6] 和 [7, 0, 1, 2] 两个部分，其中左边 [4, 5, 6] 这个部分的数组是有序的，其他也是如此。

    因此，解决思路就是在常规二分查找的时候查看当前 mid 为分割位置分割出来的两个部分 [l, mid] 和 [mid + 1, r] 哪个部分是有序的，
    并根据有序的那个部分确定我们该如何改变二分查找的上下界，因为我们能够根据有序的那部分判断出 target 在不在这个部分：
        如果 [l, mid - 1] 是有序数组，且 target 的大小满足[nums[l],nums[mid])，则我们应该将搜索范围缩小至 [l, mid - 1]，
        否则在 [mid + 1, r] 中寻找。
        如果 [mid, r] 是有序数组，且 target 的大小满足 (nums[mid+1],nums[r]]，则我们应该将搜索范围缩小至 [mid + 1, r]，
        否则在 [l, mid - 1] 中寻找。

     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) { // 左边是个有序数组
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 右边是有序的
                if (nums[mid] < target && target <= nums[len - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

}
