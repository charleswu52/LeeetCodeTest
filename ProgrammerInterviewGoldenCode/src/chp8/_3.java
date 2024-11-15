package chp8;

/**
 * @author WuChao
 * @since 2021/5/10 上午10:05
 */
public class _3 {
    /**
     * 程序员面试金典(version 6) - 面试题 08.03. 魔术索引
     * 难度: easy
     * <p>
     * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。
     * 给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。
     * 若有多个魔术索引，返回索引值最小的一个。
     *
     * <p>
     * 示例:
     * 输入：nums = [0, 2, 3, 4, 5]
     * 输出：0
     * 说明: 0下标的元素为0
     * <p>
     * 输入：nums = [1, 1, 1]
     * 输出：1
     * <p>
     * 数据范围：
     * nums长度在[1, 1000000]之间
     * 此题为原书中的 Follow-up，即数组中可能包含重复元素的版本
     */

    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) {
                return i;
            }
        }
        return -1;

    }

    /*
    二分剪枝：

    分两种情况讨论：

    第一种情况是数组中只有一个满足条件的答案。我们假设这个答案为 i，那么意味着 [0…i−1] 的值均小于自身的下标，[i+1…n−1] 的值均大于自身的下标。
    我们将整个数组每个元素减去其自身所在的下标，那么最后的答案即为 0 所在的下标，且在 0 之前的元素均为负数，0 之后的元素均为正数。
    以 [−1,0,2,4,5] 为例，减去自身下标以后以后得到 [−1,−1,0,1,1]，整个数组以 0 为分界点，前半部分均为负数，后半部分均为负数，
    因此我们可以使用二分查找在 O(logn) 的时间内找到答案 0 所在的下标，具体做法就是碰到负数舍弃左半边，碰到正数舍弃右半边即可。

    第二种情况是数组中存在多个满足条件的答案，此时我们发现整个数组不具有任何性质。以 [0,0,2,2,5] 为例，
    我们仍进行一次将每个元素减去其自身下标的操作，得到 [0,−1,0,−1,1]。目标是要找到第一个出现的 0，
    而由于数组中出现 00 的位置不确定，因此无法使用二分查找，但是我们可以依据此来进行一定程度的剪枝，我们剪枝的策略为：
        每次我们选择数组的中间元素，如果当前中间元素是满足条件的答案，那么这个位置往后的元素我们都不再考虑，只要寻找左半部分是否有满足条件的答案即可。
        否则我们需要查看左半部分是否有满足条件的答案，如果没有的话我们仍然需要在右半边寻找，使用的策略同上。

    依靠剪枝策略定义一个递归函数 getAnswer(nums, left, right) 返回数组 nums 的下标范围 [left,right] 中第一个满足条件的答案，
    如果没有返回 −1。每次选择中间的位置 mid，此时直接先递归调用数组左半部分 getAnswer(nums, left, mid - 1) 得到返回值 leftAnswer，
    如果存在则直接返回，如果不存在则比较 nums[mid] 和 mid 是否相等，如果相等则返回 mid，否则需要递归调用 getAnswer(nums, mid + 1, right)。
     */

    public int getAnswer(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (right - left) / 2 + left;
        int leftAnswer = getAnswer(nums, left, mid - 1);
        if (leftAnswer != -1) {
            return leftAnswer;
        } else if (nums[mid] == mid) {
            return mid;
        }
        return getAnswer(nums, mid + 1, right);
    }
    public int findMagicIndex2(int[] nums) {
        return getAnswer(nums, 0, nums.length - 1);
    }


}
