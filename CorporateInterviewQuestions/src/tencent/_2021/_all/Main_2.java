package tencent._2021._all;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author WuChao
 * @create 2021/7/2 11:01
 */
public class Main_2 {
    /**
     *  腾讯2021校园招聘技术类编程题汇总
     *  [编程题]第K小子串
     * 输入一个字符串 s，s 由小写英文字母组成，保证 s 长度小于等于 5000 并且大于等于 1。在 s 的所有不同的子串中，输出字典序第 k 小的字符串。
     * 字符串中任意个连续的字符组成的子序列称为该字符串的子串。
     * 字母序表示英文单词在字典中的先后顺序，即先比较第一个字母，若第一个字母相同，则比较第二个字母的字典序，依次类推，则可比较出该字符串的字典序大小。
     *
     * 输入描述:
     * 第一行输出一个字符串 s，保证 s 长度小于等于 5000 大于等于 1。
     * 第二行一个整数 k (1<= k <= 5)，保证 s 不同子串个数大于等于 k。
     *
     * 输出描述:
     * 输出一个字符串表示答案。
     *
     * 输入例子1:
     * aabb
     * 3
     *
     * 输出例子1:
     * aab
     *
     * 例子说明1:
     * 不同的子串依次为：
     * a aa aab aabb ab abb b bb
     * 所以答案为aab
     */



    public static void main(String[] args) {
        silu2();



    }

    /*
    思路1： 暴力获得所有的子串放到 set 中 然后输出 top k
    肯定超时
     */
    public static void silu1() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int k = Integer.parseInt(scanner.nextLine());
        // 先暴力试一下
        TreeSet<String> res = new TreeSet<>();
        int len = str.length();
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j+i <= len; j++) {
                res.add(str.substring(j, j + i));
            }
        }
        Iterator<String> iterator = res.iterator();
        String ant = null;
        while (k-->0 && iterator.hasNext()) {
            ant = iterator.next();
        }
        System.out.println(res.size());
        System.out.println(ant);
    }

    /**
     * 思路2：
     * 优化思路1，只枚举到不超过k长度的子串即可，
     * 为什么呢，题目中说的是整个子串子集中最小的k个子串，而我们只枚举到子串长度不超过k大小的就行，这样会不会忽略掉一些子串呢 答案是不会的
     * 思考极端情况，每个长度的子串只有一个，那么我们枚举到k个长度的子串也是足够了
     *
     * 改进后就可以轻松AC
     */

    public static void silu2() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int k = Integer.parseInt(scanner.nextLine());
        // 改进暴力试，只枚举到k长的子串
        TreeSet<String> res = new TreeSet<>();
        int len = str.length();
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j+i <= len  ; j++) {
                res.add(str.substring(j, j + i));
            }
        }
        Iterator<String> iterator = res.iterator();
        String ant = null;
        System.out.println(res);
        while (k-->0 && iterator.hasNext()) {
            ant = iterator.next();
        }
        System.out.println(res.size());

        System.out.println(ant);
    }
}
