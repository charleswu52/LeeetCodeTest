package leetcodetest.Apr;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/4/12 下午4:38
 */
public class _12 {
    /**
     * 每日一题：2021/4/12
     * 179. 最大数
     * 难度: medium
     * <p>
     * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
     * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     * <p>
     * 示例：
     * 输入：nums = [3,30,34,5,9]
     * 输出："9534330".
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 109
     */

    /*
     对原数组排序即可,不过为了使用自定义的排序函数需要把原数组做一个装箱操作，转换成Integer类型的数组
     */
    public String largestNumber(int[] nums) {
        Integer[] trans = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            trans[i] = nums[i];
        }
        Arrays.sort(trans, (o1, o2) -> {
            int o1Count = 10, o2Count = 10;// 表示 o1 和 o2 的位数，个位数就是10
            while (o1Count <= o1) {
                o1Count *= 10;
            }
            while (o2Count <= o2) {
                o2Count *= 10;
            }
            return (int) o2 * o1Count + o1 - (o2Count * o1 + o2);

        });
        System.out.println(Arrays.toString(trans));
        StringBuilder stringBuilder = new StringBuilder();
        if (trans[0] == 0) {
            return "0";
        }
        for (Integer integer : trans) {
            stringBuilder.append(integer);
        }
        return stringBuilder.toString();

    }

    @Test
    public void test() {
        int[] nums = {3,30,34,5,9};
        System.out.println(largestNumber(nums));
    }
}
