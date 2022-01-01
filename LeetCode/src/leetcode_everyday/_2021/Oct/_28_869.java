package leetcode_everyday._2021.Oct;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/10/29 10:47
 */
public class _28_869 {
    /**
     * 每日一题：2021/10/28
     * <p>
     * 869. 重新排序得到 2 的幂
     * <p>
     * 难度：medium
     * <p>
     * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
     * <p>
     * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
     * <p>
     * 示例1：
     * <p>
     * 输入：1
     * <p>
     * 输出：true
     * <p>
     * 输入：46
     * <p>
     * 输出：true
     * <p>
     * 范围
     * <p>
     * 1 <= N <= 10^9
     */

    /*
    思路：打表+DFS
    一个朴素的做法是对 n 进行重排，然后检查重排后的数值是否属于 2 的幂。

    由于 2 的幂数固定，我们可以先通过「打表」将范围落在 [1,1e9] 以内的 2 的幂预处理出来，这样我们可以在 O(1) 的复杂度内判断某个数是否为 2 的幂。

    重排的过程则是 DFS 实现。
     */

    static Set<Integer> set = new HashSet<>();
    static{
        for (int i = 1; i < (int) 1e9 + 10; i*=2) {
            set.add(i);
        }
    }

    int m;
    int[] cnts = new int[10];
    public boolean reorderedPowerOf2(int n) {
        while (n != 0) {
            cnts[n % 10]++;
            n /= 10;
            m++;
        }
        return dfs(0, 0);
    }

    public boolean dfs(int u, int cur) {
        if (u == m) {
            return set.contains(cur);
        }
        for (int i = 0; i < 10; i++) {
            if (cnts[i] != 0) {
                cnts[i]--;
                if ((i != 0 || cur != 0) && dfs(u + 1, cur * 10 + i)) {
                    return true;
                }
                cnts[i]++;
            }
        }
        return false;
    }

    /*
    思路2：预处理+哈希表
     */
    Set<String> store = new HashSet<>();
    public boolean reorderedPowerOf2_1(int n) {
        init();
        return store.contains(countDigits(n));
    }

    public void init() {
        for (int i = 1; i < (int) 1e9 + 10; i*=2) {
            store.add(countDigits(i));
        }
    }

    public String countDigits(int n) {
        char[] cnt = new char[10];
        while (n > 0) {
            ++cnt[n % 10];
            n /= 10;
        }
        return new String(cnt);
    }
}
