package _360._2021spring_2;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/24 10:50
 */
public class Main_1 {
    /**
     *【2021】360春招编程题（第二批）
     * [编程题]【2021】360编程题ab串
     *
     * 小明得到一个只包含a,b两个字符的字符串，但是小明不希望在这个字符串里a出现在b左边。
     * 现在他可以将”ab”这样的子串替换成”bba”，在原串中的相对位置不变。
     * 输出小明最少需要操作多少次才能让一个给定字符串所有a都在b的右边。
     *
     * 输入描述:
     * 一个只包含a,b字符的字符串，长度不超过100000。
     *
     * 输出描述:
     * 最小的操作次数。结果对1000000007取模。
     *
     * 输入例子1:
     * ab
     *
     * 输出例子1:
     * 1
     *
     * 例子说明1:
     * ab到bba
     *
     * 输入例子2:
     * aab
     *
     * 输出例子2:
     * 3
     *
     * 例子说明2:
     * aab到abba到bbaba到bbbbaa
     */

    /*
    思路2：参考评论区题解
    ”ab”子串替换成”bba”。
    感性得理解一下，将a移动到后面去，a每移动一位就相当在其前面增加个b。
    我们可以证明：无论那个a先开始移动，最终的移动次数都是一样的。

    假设我们要将第k-1,k,k+1个a移动在一起，其中第k个a与第k-1一个a中间有M个a，第k个a与第k+1一个a中间有N个a，
    当将第k移动到第k+1旁边时在原来的位置上增加N个数量，已经移动了N次，那么现在第k-1个与第k个a有M+2*N个数量，
    再将将第k-1移动到第k旁边时是移动次数为N+(M+2*N)=M+3*N

    同理先移动第k-1个a 再移动第k个a,将他们聚在一起的移动次数为M+3*N发现是一样的，即证明了我们的结论。

    我们在考虑时空复杂度的同时也要考虑代码的复杂度呀！
    我们干脆从后面开始枚举a的位置，当a面对b的个数移动到最右边时，b的数量会多一倍，我们只需遇到a记录一下b个数对于答案的贡献就可以啦！！！
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // 时间复杂度过高
//        int res = replaceCnt(input);
//        System.out.println(res);

        // 思路2 : 参考
        long cnt = 0;
        long ans = 0;
        int mod = 1000000007;
        for (int i = input.length()-1; i>=0 ; i--) {
            if (input.charAt(i) == 'b') {
                cnt++;
            } else {
                ans = (ans + cnt) % mod;
                cnt = cnt * 2 % mod;
            }

        }
        System.out.println(ans);

    }

    public static int replaceCnt(String str) {
        String replace = replaceStr(str);
        return replace.length() - str.length();
    }

    /**
     * 不断替换直到没有可替换的为止
     * @param str
     * @return
     */
    public static String replaceStr(String str) {
        String replace = str.replace("ab", "bba");
        if (str.equals(replace)) {
            return replace;
        }
        return replaceStr(replace);

    }

    @Test
    public void test() {
        String test = "ababsabcabab";
        String[] abs = test.split("ab");
        System.out.println(test.replace("ab", "baa"));
        System.out.println(Arrays.toString(abs));
        System.out.println(abs.length);
    }
}
