package leetcode_everyday._2022.Jul;

import javax.xml.soap.Node;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/7/7 10:47
 */
public class _7_648 {
    /**
     * 每日一题：2022/7/7
     * <p>
     * 1648. 单词替换
     * <p>
     * 难度：medium
     * <p>
     * 在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。
     * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
     * <p>
     * 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。
     * 你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
     * <p>
     * 你需要输出替换之后的句子。
     * <p>
     * 示例
     * <p>
     * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
     * <p>
     * 输出："the cat was rat by the bat"
     * <p>
     * 范围
     * <p>
     * 1 <= dictionary.length <= 1000
     * 1 <= dictionary[i].length <= 100
     * dictionary[i] 仅由小写字母组成。
     * 1 <= sentence.length <= 10^6
     * sentence 仅由小写字母和空格组成。
     * sentence 中单词的总量在范围 [1, 1000] 内。
     * sentence 中每个单词的长度在范围 [1, 1000] 内。
     * sentence 中单词之间由一个空格隔开。
     * sentence 没有前导或尾随空格。
     */

    /*
    思路：Trie  树
     */

    class TrieNode {
        boolean isWord;
        TrieNode[] trieNodes = new TrieNode[26];

    }

    TrieNode root = new TrieNode();

    void add(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int s = word.charAt(i) - 'a';
            if (p.trieNodes[s] == null) {
                p.trieNodes[s] = new TrieNode();
            }
            p = p.trieNodes[s];
        }
        p.isWord = true;
    }

    String query(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int s = word.charAt(i) - 'a';
            if (p.trieNodes[s] == null) {
                break;
            }
            if (p.trieNodes[s].isWord) {
                return word.substring(0, i + 1);
            }
            p = p.trieNodes[s];
        }
        return word;
    }




    public String replaceWords(List<String> dictionary, String sentence) {

        StringBuffer stringBuffer = new StringBuffer();
        for (String word : dictionary) {
            add(word);
        }
        for (String s : sentence.split(" ")) {
            stringBuffer.append(query(s)).append(" ");
        }

        return stringBuffer.substring(0, stringBuffer.length() - 1);

    }
}
