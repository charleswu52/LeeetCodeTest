import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/1 上午11:21
 */
public class _66 {
    /**
     * 剑指 Offer 66. 构建乘积数组
     * 难度: medium
     * <p>
     * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
     * 即B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
     *
     * <p>
     * 例如：
     * 输入: [1,2,3,4,5]
     * 输出: [120,60,40,30,24]
     *
     * <p>
     * 数据范围：
     * 所有元素乘积之和不会溢出 32 位整数
     * a.length <= 100000
     */


    /*
    题目解析：题目比较易懂，难点在于不能使用除法，只能使用除法
     */

    /**
     * 思路1：构造表格
     * B[i]的值是除A[i]之外的其余元素的乘积，因此对数组A和B可以看成一个N*N的表格，主对角线元素可以全看成1
     * 则可以将表格分为上三角和下三角两部分。分别迭代上三角和下三角部分的乘积即可不使用除法就能获得结果
     * 算法过程：
     * 初始化：数组 B ，其中 B[0] = 1 ；辅助变量 tmp = 1 ；
     * 计算 B[i] 的 下三角 各元素的乘积，直接乘入 B[i] ；
     * 计算 B[i] 的 上三角 各元素的乘积，记为 tmp ，并乘入 B[i] ；
     * 返回 B 。
     *
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        if (a.length == 0) {
            return new int[0];
        }
        int[] b = new int[a.length];
        b[0] = 1;
        int temp = 1;
        // 计算下三角部分
        for (int i = 1; i < a.length; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        // 计算上三角部分
        for (int i = a.length-2; i >= 0; i--) {
            temp *= a[i + 1];
            b[i] *= temp;
        }
        return b;

    }

    @Test
    public void test() {
        int[] a = {};
    }
}
