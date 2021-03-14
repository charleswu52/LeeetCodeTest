import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/14 上午10:00
 */
public class _17 {
    /**
     * 剑指 Offer 17. 打印从1到最大的n位数
     * 难度: easy
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     * <p>
     * 示例：
     * 输入: n = 1
     * 输出: [1,2,3,4,5,6,7,8,9]
     *
     * <p>
     * 数据范围：
     */

    public int[] printNumbers(int n) {
        int[] res = new int[(int) Math.pow(10, n)-1];
        for (int i = 0; i < res.length; i++) {
            res[i] = i + 1;
        }
        return res;


    }
    public void sword2Offer_17() {
        int n = 4;
        System.out.println(Arrays.toString(printNumbers(n)));

    }
}
