/**
 * @author WuChao
 * @since 2021/3/26 上午8:53
 */
public class _53_1 {
    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     * 难度: easy
     * <p>
     * 统计一个数字在排序数组中出现的次数。
     * <p>
     * 示例：
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: 2
     *
     * <p>
     * 数据范围：
     * 0 <= 数组长度 <= 50000
     */


    /**
     * 暴力一次遍历，没有算法 O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int res = 0;
        for (int num : nums) {
            if (target == num) {
                res++;
            }
        }
        return res;
    }

    /**
     * 二分法:调用查找左右边界的模板方法
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        if (nums.length < 1) {
            return 0;
        }
        int left = left_bound(nums, target);
        int right = right_bound(nums, target);
        if (left == -1 && right == -1) {
            return 0;
        } else if (left == -1 || right == -1) {
            return 1;
        } else {
            return right - left;
        }
    }

    /**
     * 查找目标元素的左侧边界
     *
     * @param nums
     * @param target
     * @return
     */
    public int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1;
            }
        }
        // 最后要检查 left 越界的情况
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }

    /**
     * 查找目标元素的右侧边界
     *
     * @param nums
     * @param target
     * @return
     */
    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1;
            }
        }
        // 最后要检查 right 越界的情况
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }


    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public int search3(int[] nums, int target) {
        // 搜索右边界 right
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] <= target) i = m + 1;
            else j = m - 1;
        }
        int right = i;
        // 若数组中无 target ，则提前返回
        if(j >= 0 && nums[j] != target) return 0;
        // 搜索左边界 right
        i = 0; j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] < target) i = m + 1;
            else j = m - 1;
        }
        int left = j;
        return right - left - 1;

    }

}
