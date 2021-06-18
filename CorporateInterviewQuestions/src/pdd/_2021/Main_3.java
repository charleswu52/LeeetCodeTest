package pdd._2021;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/6/18 11:28
 */
public class Main_3 {
    /**
     * 拼多多2021笔试真题集
     * [编程题]多多的求和计算
     * <p>
     * 多多路上从左到右有N棵树（编号1～N），其中第i个颗树有和谐值Ai。
     * 多多鸡认为，如果一段连续的树，它们的和谐值之和可以被M整除，那么这个区间整体看起来就是和谐的。
     * 现在多多鸡想请你帮忙计算一下，满足和谐条件的区间的数量。
     * <p>
     * 输入描述:
     * 第一行，有2个整数N和M，表示树的数量以及计算和谐值的参数。
     * （ 1 <= N <= 100,000, 1 <= M <= 100  ）
     * 第二行，有N个整数Ai, 分别表示第i个颗树的和谐值。
     * （ 0 <= Ai <= 1,000,000,000 ）
     * <p>
     * 输出描述:
     * 共1行，每行1个整数，表示满足整体是和谐的区间的数量。
     * <p>
     * 输入例子1:
     * 5 2
     * 1 2 3 4 5
     * <p>
     * 输出例子1:
     * 6
     * <p>
     * 例子说明1:
     * 长度为1: [2], [4]
     * 长度为2: 无
     * 长度为3: [1,2,3], [3,4,5]
     * 长度为4: [1,2,3,4], [2,3,4,5]
     * 长度为5: 无
     * 共6个区间的和谐值之和可以被2整除。
     */


    /*
    思路：使用前缀和+map

     */
    public static void main(String[] args) {
        int n, m;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] trees = new int[n];
        for (int i = 0; i < n; i++) {
            trees[i] = scanner.nextInt();
            trees[i] = trees[i] % m;
        }

        long res = 0;

        // 前缀和
        int[] preSums = new int[n];
        preSums[0] = trees[0];
        for (int i = 1; i < n; i++) {
            preSums[i] = (preSums[i - 1] + trees[i]) % m;
        }


        Map<Integer, Integer> store = new HashMap<>();
        store.put(0, 1);
        for (int preSum : preSums) {
            if (store.containsKey(preSum)) {
                res += store.get(preSum);
            }
            store.put(preSum, store.getOrDefault(preSum, 0) + 1);
        }



        System.out.println(res);
        System.out.println(Arrays.toString(preSums));
        Iterator<Map.Entry<Integer, Integer>> iterator = store.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            System.out.println(next.getKey()+" : "+next.getValue());
        }


//        System.out.println(Arrays.toString(perSum));
//        System.out.println(Arrays.deepToString(store));


    }
}
