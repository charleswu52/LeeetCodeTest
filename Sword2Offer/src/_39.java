import org.junit.Test;

import java.util.HashMap;

/**
 * @author WuChao
 * @since 2021/3/20 下午12:42
 */
public class _39 {
    /**
     * 剑指 Offer 39. 数组中出现次数超过一半的数字
     * 难度: easy
     * <p>
     * <p>
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * <p>
     * 示例：
     * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
     * 输出: 2
     *
     * <p>
     * 数据范围：
     * 1 <= 数组长度 <= 50000
     */

    public int majorityElement(int[] nums) {
        int len = nums.length;
        HashMap<Integer, Integer> store = new HashMap<>();
        for (int num : nums) {
            store.put(num, store.getOrDefault(num, 0) + 1);
            if (store.get(num) > (len) / 2) {
                return num;
            }
        }
        throw new RuntimeException("题目所给数组不符合条件！");

    }

    @Test
    public void test() {
        int[] nums = {1, 2};
        System.out.println(majorityElement(nums));

    }
}
