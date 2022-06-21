package leetcode_everyday._2022.Jun;

/**
 * @author WuChao
 * @create 2022/6/21 11:28
 */
public class _21_1108 {
    /**
     * 每日一题：2022/6/21
     * <p>
     * 1108. IP 地址无效化
     * <p>
     * 难度：easy
     * <p>
     * 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
     * <p>
     * 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
     * <p>
     * 示例
     * <p>
     * 输入：address = "1.1.1.1"
     * <p>
     * 输出："1[.]1[.]1[.]1"
     * <p>
     * 范围
     * <p>
     * 给出的 address 是一个有效的 IPv4 地址
     */

    /*
    思路：
     */
    public String defangIPaddr(String address) {
        int len = address.length();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < len; i++) {
            if (address.charAt(i) == '.') {
                stringBuffer.append("[.]");
            } else {
                stringBuffer.append(address.charAt(i));
            }
        }
        return stringBuffer.toString();


    }
}
