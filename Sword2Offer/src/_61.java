import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/31 上午10:50
 */
public class _61 {
    /**
     * 剑指 Offer 61. 扑克牌中的顺子
     * 难度: easy
     * <p>
     * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，
     * 而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     *
     *
     * <p>
     * 例如：
     * 输入: [1,2,3,4,5]
     * 输出: True
     * <p>
     * 输入: [0,0,1,2,5]
     * 输出: True
     * <p>
     * 数据范围：
     * 数组长度为 5
     * 数组的数取值为 [0, 13] .
     */

    /**
     * 思路：排序+遍历
     * 1.先判断重复：非0元素不能重复
     * 2。排序后最大数组是nums[4],最小的非0元素是nums[joker],其中joker是统计的前面0元素的数量
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zeroCount = 0;
        int minusCount = 0;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            }else{
                if (nums[i] == nums[i + 1]) {
                    return false;
                }
                if (nums[i] + 1 != nums[i+1]) {
                    minusCount += nums[i+1] - nums[i] - 1;
                }
            }
        }
        return minusCount<=zeroCount;
    }

    /**
     * 题解：
     * 排序+一次遍历
     * @param nums
     * @return
     */

    public boolean isStraight2(int[] nums) {
        int joker = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] == 0) {
                joker++;
            } else if (nums[i] == nums[i + 1]) {
                return false;
            }
        }
        return nums[4] - nums[joker] < 5;
    }


        @Test
    public void test() {

    }
}
