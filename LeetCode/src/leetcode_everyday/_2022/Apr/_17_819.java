package leetcode_everyday._2022.Apr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2022/4/17 8:20
 */
public class _17_819 {
    /**
     * 每日一题：2022/4/17
     * <p>
     * 819. 最常见的单词
     * <p>
     * 难度：hard
     * <p>
     * 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
     * <p>
     * 题目保证至少有一个词不在禁用列表中，而且答案唯一。
     * <p>
     * 禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
     * <p>
     * 示例
     * <p>
     * 输入:
     * <p>
     * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
     * banned = ["hit"]
     * <p>
     * 输出: "ball"
     * <p>
     * 解释:
     * "hit" 出现了3次，但它是一个禁用的单词。
     * "ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。
     * 注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"），
     * "hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
     * <p>
     * 范围
     * <p>
     * 1 <= 段落长度 <= 1000
     * 0 <= 禁用单词个数 <= 100
     * 1 <= 禁用单词长度 <= 10
     * 答案是唯一的, 且都是小写字母 (即使在 paragraph 里是大写的，即使是一些特定的名词，答案都是小写的。)
     * paragraph 只包含字母、空格和下列标点符号!?',;.
     * 不存在没有连字符或者带有连字符的单词。
     * 单词里只包含字母，不会出现省略号或者其他标点符号。
     */

    /*
    思路：哈希表 简单字符串模拟
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] strings = paragraph.toLowerCase().replace('!', ' ').replace('?', ' ').replace('\'', ' ').replace(',', ' ').replace(';', ' ').replace('.', ' ').trim().split(" ");
        Map<String,Integer> map1=new HashMap<>();
        Map<String,Integer> map2=new HashMap<>();
        //被禁用的单词放入map1
        for(String str:banned){
            map1.put(str,1);
        }
        //把没有禁用的单词放入map2
        for(String str : strings){
            if(!map1.containsKey(str)&&!str.equals("")){
                map2.put(str,map2.getOrDefault(str,0)+1);
            }
        }
        //在map2找出出现最多的单词
        int max=-1;
        String res=null;
        for(String str:map2.keySet()){
            if(map2.get(str)>max){
                max=map2.get(str);
                res=str;
            }
        }
        return res;

    }
}
