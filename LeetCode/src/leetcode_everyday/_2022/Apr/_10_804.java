package leetcode_everyday._2022.Apr;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2022/4/10 8:37
 */
public class _10_804 {
    /**
     * 每日一题：2022/4/10
     * <p>
     * 804. 唯一摩尔斯密码词
     * <p>
     * 难度：easy
     * <p>
     * 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如:
     * <p>
     * 'a' 对应 ".-" ，
     * 'b' 对应 "-..." ，
     * 'c' 对应 "-.-." ，以此类推。
     * 为了方便，所有 26 个英文字母的摩尔斯密码表如下：
     * <p>
     * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",
     * ".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
     * 给你一个字符串数组 words ，每个单词可以写成每个字母对应摩尔斯密码的组合。
     * <p>
     * 例如，"cab" 可以写成 "-.-..--..." ，(即 "-.-." + ".-" + "-..." 字符串的结合)。我们将这样一个连接过程称作 单词翻译 。
     * 对 words 中所有单词进行单词翻译，返回不同 单词翻译 的数量。
     *
     * <p>
     * 示例
     * <p>
     * 输入: words = ["gin", "zen", "gig", "msg"]
     * <p>
     * 输出: 2
     * <p>
     * 解释:
     * 各单词翻译如下:
     * "gin" -> "--...-."
     * "zen" -> "--...-."
     * "gig" -> "--...--."
     * "msg" -> "--...--."
     * <p>
     * 共有 2 种不同翻译, "--...-." 和 "--...--.".
     * <p>
     * 范围
     * <p>
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 12
     * words[i] 由小写英文字母组成
     */


    /*
    思路1：简单字符串set
     */
    String[] strings = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-",
            ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        for (String word : words) {
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : word.toCharArray()) {
                stringBuilder.append(strings[c - 'a']);
            }
            set.add(stringBuilder.toString());
        }
        return set.size();


    }
}
