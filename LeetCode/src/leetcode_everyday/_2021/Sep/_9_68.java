package leetcode_everyday._2021.Sep;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/9/9 上午9:12
 */
public class _9_68 {
    /**
     * 每日一题：2021/9/9
     * 68. 文本左右对齐
     * 难度：hard
     * <p>
     * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
     *
     * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
     *
     * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
     *
     * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
     *
     * 说明:
     *
     * 单词是指由非空格字符组成的字符序列。
     * 每个单词的长度大于 0，小于等于 maxWidth。
     * 输入单词数组 words 至少包含一个单词。
     * <p>
     * 示例 1：
     * 输入:
     * words = ["This", "is", "an", "example", "of", "text", "justification."]
     * maxWidth = 16
     * 输出:
     * [
     *    "This    is    an",
     *    "example  of text",
     *    "justification.  "
     * ]
     *
     * 示例 2:
     * 输入:
     * words = ["What","must","be","acknowledgment","shall","be"]
     * maxWidth = 16
     * 输出:
     * [
     *   "What   must   be",
     *   "acknowledgment  ",
     *   "shall be        "
     * ]
     * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     *      因为最后一行应为左对齐，而不是左右两端对齐。
     *      第二行同样为左对齐，这是因为这行只包含一个单词。
     *
     * 示例 3:
     * 输入:
     * words = ["Science","is","what","we","understand","well","enough","to","explain",
     *          "to","a","computer.","Art","is","everything","else","we","do"]
     * maxWidth = 20
     * 输出:
     * [
     *   "Science  is  what we",
     *   "understand      well",
     *   "enough to explain to",
     *   "a  computer.  Art is",
     *   "everything  else  we",
     *   "do                  "
     * <p>
     * 注意:
     */


    /*
    思路：贪心思想
    对于每一行，我们首先确定最多可以放置多少单词，这样可以得到该行的空格个数，从而确定该行单词之间的空格个数。
    对于每一行填充细节，分三种情况讨论：
        1.当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格；
        2.当前行不是最后一行，且只有一个单词：该单词左对齐，在行末填充空格；
        3.当前行不是最后一行，且不只一个单词：设当前行单词数为 numWords，空格数为 numSpaces，我们需要将空格均匀分配在单词之间，
            则单词之间应至少有avgSpaces=⌊ numSpaces / (numWords−1) ⌋ 个空格，对于多出来的
            extraSpaces=numSpaces mod (numWords−1) 个空格，应填在前 extraSpaces 个单词之间。因此，前 extraSpaces 个单词之间填充
            avgSpaces+1 个空格，其余单词之间填充 avgSpaces 个空格。
     */

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int right = 0, n = words.length;
        while (true) {
            int left = right;// 当前行的第一个单词在 words 的位置
            int sumLen = 0; // 统计这一行单词长度之和
            // 循环确定当前行可以放多少单词，注意单词之间应至少有一个空格
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }
            // 当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
            if (right == n) {
                StringBuffer stringBuffer = join(words, left, n, " ");
                stringBuffer.append(blank(maxWidth - stringBuffer.length()));
                ans.add(stringBuffer.toString());
                return ans;
            }

            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;
            // 当前行只有一个单词：该单词左对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuffer stringBuffer = new StringBuffer(words[left]);
                stringBuffer.append(blank(numSpaces));
                ans.add(stringBuffer.toString());
                continue;
            }
            // 当前行不只一个单词
            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1)));// 拼接额外加一个空格的单词
            stringBuffer.append(blank(avgSpaces));
            stringBuffer.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces))); // 拼接其余单词
            ans.add(stringBuffer.toString());
        }
    }

    /**
     * blank 返回长度为 n 的由空格组成的字符串
     * @param n
     * @return
     */
    public String blank(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    /**
     * join 返回用 sep 拼接 [left, right) 范围内的 words 组成的字符串
     * @param words
     * @param left
     * @param right
     * @param sep
     * @return
     */
    public StringBuffer join(String[] words, int left, int right, String sep) {
        StringBuffer sb = new StringBuffer(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(sep);
            sb.append(words[i]);
        }
        return sb;
    }

}
