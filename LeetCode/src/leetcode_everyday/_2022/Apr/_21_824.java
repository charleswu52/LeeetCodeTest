package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/21 8:46
 */
public class _21_824 {
    /**
     * 每日一题：2022/4/21
     * <p>
     * 824. 山羊拉丁文
     * <p>
     * 难度：easy
     * <p>
     * 给你一个由若干单词组成的句子 sentence ，单词间由空格分隔。每个单词仅由大写和小写英文字母组成。
     * <p>
     * 请你将句子转换为 “山羊拉丁文（Goat Latin）”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。山羊拉丁文的规则如下：
     * <p>
     * 如果单词以元音开头（'a', 'e', 'i', 'o', 'u'），在单词后添加"ma"。
     * 例如，单词 "apple" 变为 "applema" 。
     * 如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
     * 例如，单词 "goat" 变为 "oatgma" 。
     * 根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从 1 开始。
     * 例如，在第一个单词后添加 "a" ，在第二个单词后添加 "aa" ，以此类推。
     * 返回将 sentence 转换为山羊拉丁文后的句子。
     * <p>
     * 示例
     * <p>
     * 输入：sentence = "The quick brown fox jumped over the lazy dog"
     * <p>
     * 输出："heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
     * <p>
     * 范围
     * <p>
     * 1 <= sentence.length <= 150
     * sentence 由英文字母和空格组成
     * sentence 不含前导或尾随空格
     * sentence 中的所有单词由单个空格分隔
     */
    /*
    思路： 简单字符串构造
     */
    public String toGoatLatin(String sentence) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = sentence.split(" ");
        for (int i = 0; i < strings.length; i++) {
            StringBuilder temp = new StringBuilder();
            char c = strings[i].charAt(0);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'||c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                temp.append(strings[i]).append("ma");
            } else {
                temp.append(strings[i].substring(1)).append(c).append("ma");
            }
            for (int j = 0; j <= i; j++) {
                temp.append('a');
            }
            stringBuilder.append(temp).append(" ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);

    }

}
