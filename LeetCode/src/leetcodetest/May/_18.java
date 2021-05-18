package leetcodetest.May;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @since 2021/5/18 上午8:38
 */
public class _18 {
    /**
     * 每日一题：2021/5/18
     * 1442. 形成两个异或相等数组的三元组数目
     * 难度: medium
     * <p>
     * 给你一个整数数组 arr 。
     * <p>
     * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
     * <p>
     * a 和 b 定义如下：
     * <p>
     * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
     * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     * 注意：^ 表示 按位异或 操作。
     * <p>
     * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     *
     * <p>
     * 示例：
     * 输入：arr = [2,3,1,6,7]
     * 输出：4
     * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
     * <p>
     * 输入：arr = [1,1,1,1,1]
     * 输出：10
     * <p>
     *
     * <p>
     * 数据范围：
     * 1 <= arr.length <= 300
     * 1 <= arr[i] <= 10^8
     */

    /*
    题目解析：根据异或运算的性质，本来题目是要找三元组，即三个下标范围，(i,j,k),使得arr[i]^..^arr[j-1] = arr[j]^...^arr[k]
    直接按照它题目的要求是不好想的，以为是要使用滑动窗口然后分割求前面部分的异或值和后面部分的异或值，但是怎么分割又是一个很难的问题，不好进行下去

    参看官方题解，给出一个关键词：异或前缀和
    这才有点恍然大悟；我们顺着这个提示来想:
        定义S[i]为数组的异或前缀和，S[i] = 0,i=0;S[i] = arr[0]^...^arr[i-1],1<i<=n
        则 S[i] ^ S[j] = arr[i]^...^arr[j-1]，这不就是题目中说的(i,j,k)中（i，j）的含义同理（j,k）也可得出，但是注意题目条件j<=k
        因此（j,k） = S[j]^S[k+1]
       继而 S[i] ^ S[j] = S[j] ^S[k+1] ==> S[i] = S[k+1] (i<k<n)
    分析完成后，来做这个题目
     */
    /*
    思路1：两重循环
     */
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int total = 0;
        int[] s = new int[n + 1];
        s[0] = total;
        for (int i = 1; i < n + 1; i++) {
            total ^= arr[i - 1];
            s[i] = total;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                if (s[i] == s[k + 1]) {// 所有满足该条件的内的j都是符合条件的
                    res += k - i;
                }
            }
        }
        return res;
    }

    /*
    改进思路：使用哈希表存储
    对于下标 kk，若下标 i=i_1,i_2,...,i_m 时均满足 S_i=S_k+1，根据方法二，这些二元组 (i_1,k),(i_2,k),...,(i_m,k)对答案的贡献之和为
        (k-i_1)+(k-i_2)+...+(k-i_m)=m*k-(i_1+i_2+...+i_m)
    也就是说，当遍历下标 k 时，我们需要知道所有满足 S_i=S_k+1 :
        下标 i 的出现次数 m
        下标 i 之和
    这可以借助两个哈希表来做到，在遍历下标 k 的同时，一个哈希表统计 S_k 的出现次数，另一个哈希表统计值为 S_k 的下标之和。
     */

    public int countTriplets2(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] ^ arr[i];
        }
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        Map<Integer, Integer> total = new HashMap<Integer, Integer>();
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            if (cnt.containsKey(s[k + 1])) {
                ans += cnt.get(s[k + 1]) * k - total.get(s[k + 1]);
            }
            cnt.put(s[k], cnt.getOrDefault(s[k], 0) + 1);
            total.put(s[k], total.getOrDefault(s[k], 0) + k);
        }
        return ans;


    }


        @Test
    public void test() {
        int[] arr = {1,1,1,1,1};
        System.out.println(countTriplets(arr));
        System.out.println(countTriplets2(arr));
    }
}
