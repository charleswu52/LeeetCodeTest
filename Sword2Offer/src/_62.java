import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/31 下午12:42
 */
public class _62 {
    /**
     * 剑指 Offer 62. 圆圈中最后剩下的数字
     * 难度: easy
     * <p>
     * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
     *
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     *
     * <p>
     * 例如：
     * 输入: n = 10, m = 17
     * 输出: 2
     * <p>
     * 数据范围：
     * 1 <= n <= 10^5
     * 1 <= m <= 10^6
     */

    /**
     * 思路：模拟删除过程
     * 建立0 -  n-1的动态数组，然后对下标循环删除直到动态数组中只剩下一个元素
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        List<Integer> store = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            store.add(i);
        }
        int  i ;
        for (i = 0; i < store.size() && store.size() > 1; ) {
            int delIdx = (i + m - 1) % store.size();
            store.remove(delIdx);
            i = delIdx % store.size();
        }
        return store.get(i);

    }

    @Test
    public void test() {
        int n = 10, m = 17;
        System.out.println(lastRemaining(n, m));

    }

}
