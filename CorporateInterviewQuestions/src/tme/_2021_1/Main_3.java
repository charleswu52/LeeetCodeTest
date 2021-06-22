package tme._2021_1;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/6/21 16:05
 */
public class Main_3 {
    /**
     * 腾讯音乐娱乐（TME）2021暑期实习生招聘技术类笔试题
     * [编程题]最小代价
     * <p>
     * 给你一个数组,让第个数加一的代价是b_i,你可以求出让数组a,每个数各不相同的最小代价吗?
     * <p>
     * 输入描述:
     * 第一行 1 个整数 n ,表示数组长度 (1<=n<=1e5)
     *
     * 第二行 n 个整数a_i,表示数组a(1<=a_i<1e9)
     *
     * 第三行 n 个整数b_i,表示第 i 个增加 1 的代价(1<=bi<=1e5)
     * <p>
     * 输出描述:
     * 一个整数表示结果.
     *
     * 输入例子1:
     * 5
     * 1 2 3 4 5
     * 1 1 1 1 1
     *
     * 输出例子1:
     * 0
     *
     * 例子说明1:
     * 不用任何操作
     *
     * 输入例子2:
     * 3
     * 1 1 2
     * 4 5 3
     *
     * 输出例子2:
     * 7
     *
     * 例子说明2:
     * 先把第1个数字1加1，此时代价为4，a数组为2 1 2。然后再把第三个数字2加1，此时代价为4+3=7，a数组为2 1 3。
     * 8
     */

    /*
    思路：
    贪心策略 ? DP ?
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String first = scanner.nextLine();
        String[] firsts = first.split(" ");
        String second = scanner.nextLine();
        String[] seconds = second.split(" ");
        Map<Integer, List<Integer>> costList = new TreeMap<>();
        for (int i = 0; i < firsts.length; i++) {
            int num = Integer.parseInt(firsts[i]);
            int cost = Integer.parseInt(seconds[i]);
            List<Integer> orDefault = costList.getOrDefault(num, new ArrayList<>());
            orDefault.add(cost);
            costList.put(num, orDefault);
        }
        int res = 0;
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = costList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> next = iterator.next();
            Integer key = next.getKey();
            List<Integer> value = next.getValue();

        }


    }
}
