import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @since 2021/3/9 上午8:51
 */
public class _03 {
    /**
     * 剑指 Offer 03. 数组中重复的数字
     * 难度: easy
     * 找出数组中重复的数字。
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
     * 请找出数组中任意一个重复的数字。
     * <p>
     * 示例:
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     * <p>
     * 数据范围：
     * 2 <= n <= 100000
     */


    /**
     * 思路1：哈希表：使用map将数据存储
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        HashMap<Integer, Integer> store = new HashMap<>();
        for (int num : nums) {
            if (!store.containsKey(num)) {
                store.put(num, 1);
            } else {
                return num;
            }
        }
        return 0;
    }

    /**
     * 思路2：使用set存储
     */
    public int findRepeatNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            } else {
                set.add(num);
            }
        }
        return 0;
    }
    /**
     * 思路3：使用排序，然后遍历
     */


    /**
     * 思路4：原地置换法(该题最优解)
     * 因为给定的数组是0 - n-1这个范围内，因而比如nums[0]假设是2，那么我们可以放到2这个位置，
     * 这样，一旦遇到重复的数字，那么在同一个位置就会发生碰撞，我们就可以检测出重复的数字，
     * 利用这巧妙的方法，时间复杂度为o(n)，而空间复杂度为o(1)
     */
    public int findRepeatNumber4(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }


    public void sword2Offer_03() {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(nums));
        System.out.println(findRepeatNumber2(nums));
        System.out.println(findRepeatNumber4(nums));
    }

}
