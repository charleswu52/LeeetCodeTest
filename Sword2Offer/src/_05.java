/**
 * @author WuChao
 * @since 2021/3/10 上午9:57
 */
public class _05 {
    /**
     * 剑指 Offer 05. 替换空格
     * 难度: easy
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     * <p>
     * 数据范围：
     */

    /**
     * 快捷方式，但应该不是要考察的点
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }

    /**
     * 手动实现,java中的字符串是不可变的！因此说时空复杂度均为O(n)
     * @param s
     * @return
     */
    public String replaceSpace2(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character ch : s.toCharArray()) {
            if (ch == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

     public void sword2Offer_05() {
         String s = "We are happy.";
         System.out.println(replaceSpace2(s));
     }
}