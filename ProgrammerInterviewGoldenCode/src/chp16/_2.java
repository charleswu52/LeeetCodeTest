package chp16;

import java.util.HashMap;

/**
 * @author WuChao
 * @since 2021/5/27 上午9:57
 */
public class _2 {
    /**
     * 程序员面试金典(version 6) - 面试题 16.02. 单词频率
     * 难度: medium
     * <p>
     * 设计一个方法，找出任意指定单词在一本书中的出现频率。
     * 你的实现应该支持如下操作：
     * WordsFrequency(book)构造函数，参数为字符串数组构成的一本书
     * get(word)查询指定单词在书中出现的频率
     *
     * <p>
     * 示例:
     * WordsFrequency wordsFrequency = new WordsFrequency({"i", "have", "an", "apple", "he", "have", "a", "pen"});
     * wordsFrequency.get("you"); //返回0，"you"没有出现过
     * wordsFrequency.get("have"); //返回2，"have"出现2次
     * wordsFrequency.get("an"); //返回1
     * wordsFrequency.get("apple"); //返回1
     * wordsFrequency.get("pen"); //返回1
     * <p>
     * 数据范围：
     * book[i]中只包含小写字母
     * 1 <= book.length <= 100000
     * 1 <= book[i].length <= 10
     * get函数的调用次数不会超过100000
     */

    class WordsFrequency {

        private HashMap<String, Integer> hashMap;
        public WordsFrequency(String[] book) {
            this.hashMap = new HashMap<>();
            for (String s : book) {
                if (hashMap.containsKey(s)) {
                    hashMap.put(s, hashMap.getOrDefault(s, 0) + 1);
                } else {
                    hashMap.put(s, 1);
                }
            }
        }

        public int get(String word) {
            return hashMap.getOrDefault(word, 0);
        }
    }
}
