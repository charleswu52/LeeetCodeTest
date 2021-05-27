package chp16;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/5/27 上午9:38
 */
public class _1 {
    /**
     * 程序员面试金典(version 6) - 面试题 16.01. 交换数字
     * 难度: medium
     * <p>
     * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
     * <p>
     * 示例:
     * 输入: numbers = [1,2]
     * 输出: [2,1]
     * <p>
     * 数据范围：
     * numbers.length == 2
     * -2147483647 <= numbers[i] <= 2147483647
     */

    /*
    思路：数学思路
     */
    public int[] swapNumbers(int[] numbers) {
        int xor = numbers[0] ^ numbers[1];
        numbers[0] = xor ^ numbers[0];
        numbers[1] = xor ^ numbers[1];
        return numbers;
    }
    public int[] swapNumbers2(int[] numbers) {
        numbers[0] = numbers[0] - numbers[1];
        numbers[1] = numbers[1] + numbers[0];
        numbers[0] = numbers[1] - numbers[0];
        return numbers;
    }



        @Test
    public void test(){
        int[] numbers = {1, 2};
//        System.out.println(Arrays.toString(swapNumbers(numbers)));
        System.out.println(Arrays.toString(swapNumbers2(numbers)));
    }
}
