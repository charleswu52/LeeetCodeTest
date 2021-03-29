import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/29 上午9:10
 */
public class _58_2 {
    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * 难度: easy
     * <p>
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     *
     * <p>
     * 例如：
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     * <p>
     * 数据范围：
     * 1 <= k < s.length <= 10000
     */

    public String reverseLeftWords(String s, int n) {
        String res = s.substring(n);
        res += s.substring(0, n);
        return res;
    }

    @Test
    public void test() {
        String s = "lrloseumgh";
        int n = 2;
        System.out.println(reverseLeftWords(s, n));

    }
}
