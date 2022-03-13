package leetcode_everyday._2022.Mar;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2022/3/13 8:47
 */
public class _13_393 {
    /**
     * 每日一题：2022/3/13
     * <p>
     * 393. UTF-8 编码验证
     * <p>
     * 难度：medium
     * <p>
     * 给定一个表示数据的整数数组 data ，返回它是否为有效的 UTF-8 编码。
     * <p>
     * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
     * <p>
     * 对于 1 字节 的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
     * 对于 n 字节 的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。
     * 剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
     * 这是 UTF-8 编码的工作方式：
     * Char. number range  |        UTF-8 octet sequence
     * (hexadecimal)    |              (binary)
     * --------------------+---------------------------------------------
     * 0000 0000-0000 007F | 0xxxxxxx
     * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
     * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
     * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
     * <p>
     * 注意：输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
     * <p>
     * 示例1
     * <p>
     * 输入：data = [235,140,4]
     * <p>
     * 输出：false
     * <p>
     * 解释：数据表示 8 位的序列: 11101011 10001100 00000100.
     * 前 3 位都是 1 ，第 4 位为 0 表示它是一个 3 字节字符。
     * 下一个字节是开头为 10 的延续字节，这是正确的。
     * 但第二个延续字节不以 10 开头，所以是不符合规则的。
     * <p>
     * 范围
     * <p>
     * 1 <= data.length <= 2 * 10^4
     * 0 <= data[i] <= 255
     */

    /*
    题目解析出的条件：
    一字节码：第一字节<128；
    二字节码：192<=第一字节<224；
    三字节码：224<=第一字节<240；
    四字节码：248>第一字节>=240；
    其他大小的第一字节非法；大于二字节的码，后边的字节都大于等于128小于192
     */
    public boolean validUtf8(int[] data) {
        int n = data.length;
        for (int i = 0; i < n; i++) {
            if (data[i] < 128) {
                continue;
            }
            if (data[i] < 192 || data[i] >= 248) {
                return false;
            }
            if (data[i] < 224) {
                if (i + 2 > n) {
                    return false;
                }
                if (data[i + 1] < 128 || data[i + 1] >= 192) {
                    return false;
                }
                i++;
            } else if (data[i] < 240) {
                if (i + 3 > n) {
                    return false;
                }
                for (int j = 1; j <= 2; j++) {
                    if (data[i + j] < 128 || data[i + j] >= 192) {
                        return false;
                    }
                }
                i += 2;
            } else {
                if (i + 4 > n) {
                    return false;
                }
                for (int j = 1; j <= 3; j++) {
                    if (data[i + j] < 128 || data[i + j] >= 192) {
                        return false;
                    }
                }
                i += 3;
            }
        }

        return true;

    }

    @Test
    public void test() {
        int[] data = {235, 140, 4};
        System.out.println(validUtf8(data));
    }

    public String binaryString(int num) {
        StringBuilder result = new StringBuilder();
        int flag = 1 << 7;
        for (int i = 0; i < 8; i++) {
            int val = (flag & num) == 0 ? 0 : 1;
            result.append(val);
            num <<= 1;
        }
        return result.toString();
    }
}
