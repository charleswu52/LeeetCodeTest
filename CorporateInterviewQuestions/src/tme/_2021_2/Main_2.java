package tme._2021_2;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/6/20 13:41
 */
public class Main_2 {
    /**
     * 腾讯音乐娱乐（TME）2021暑期实习生招聘技术类笔试题
     * [编程题]狡猾的雇主
     *
     * 一位雇主想要招聘一个工人，有 n 个人前来应聘，这位雇主让每个人写下期望的薪资，然后再进行选择。
     * 因为这个雇主十分狡猾，ta会选择期望薪资最低的人，但是这个人可能不是唯一的，为了避免纠纷，
     * ta会选择在没有和其他人提出的期望薪资相同的情况下期望薪资最低的人录用。
     * 求这个最低薪资，如果没有合适的人，则输出 -1
     *
     * 第一行一个整数
     * 第二行个整数表示个人提出的期望薪资
     * 保证所有输入的数为正整数且不超过
     *
     * 输出描述:
     * 一行一个整数表示答案
     *
     * 输入例子1:
     * 3
     * 3 2 1
     *
     * 输出例子1:
     * 1
     *
     * 输入例子2:
     * 6
     * 1 1 4 5 1 4
     *
     * 输出例子2:
     * 5
     *
     * 输入例子3:
     * 3
     * 4 4 4
     *
     * 输出例子3:
     * -1
     */

    /*
    简单题，使用Map存储每个值出现的次数，遍历一遍即可
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int res = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int temp = scanner.nextInt();
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            if (next.getValue() == 1 && next.getKey() < res) {
                res = next.getKey();
            }
        }
        if (res != Integer.MAX_VALUE) {
            System.out.println(res);
        } else {
            System.out.println(-1);
        }
    }
}
