package chp16;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/6/3 上午10:06
 */
public class _24 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.24. 数对和
     * 难度: medium
     * <p>
     * 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
     *
     * <p>
     * 示例:
     * 输入: nums = [5,6,5,6], target = 11
     * 输出: [[5,6],[5,6]]
     *
     *
     * <p>
     * 数据范围：
     * nums.length <= 100000
     */


    public List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        for(int num1 : map.keySet()) {
            int num2 = target - num1;
            //能够组成数对
            if(map.containsKey(num2) && map.get(num2) > 0) {
                // 统计组成数对的个数,一次把重复的都加进去
                int count = 0;
                if(num1 == num2)
                    count = map.get(num1) / 2;
                else
                    count = Math.min(map.get(num1), map.get(num2));
                //添加进列表
                for(int i = 0; i < count; i++)
                    res.add(Arrays.asList(num1, num2));
                map.put(num1, 0);
                map.put(num2, 0);
            }
        }
        return res;

    }

    @Test
    public void test() {
        int[] nums = {-2,-1,0,3,5,6,7,9,13,14};
        int target = 12;
        System.out.println(pairSums(nums, target));
    }

}
