package leetcode_everyday.Dec;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/12/26 上午9:25
 */public class _26_1078 {
    /**
     * 每日一题：2021/12/26
     * <p>
     * 1078. Bigram 分词
     * <p>
     * 难度：easy
     * <p>
     * 给出第一个词 first 和第二个词 second，考虑在某些文本 text 中可能以 "first second third" 形式出现的情况，
     * 其中 second 紧随 first 出现，third 紧随 second 出现。
     *
     * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
     * <p>
     * 示例 1：
     * <p>
     * 输入：text = "alice is a good girl she is a good student", first = "a", second = "good"
     *
     * 输出：["girl","student"]
     * <p>
     * 范围
     * <p>
     * 1 <= text.length <= 1000
     * text 由小写英文字母和空格组成
     * text 中的所有单词之间都由 单个空格字符 分隔
     * 1 <= first.length, second.length <= 10
     * first 和 second 由小写英文字母组成
     **/

    /*
    思路：简单字符串模拟
     */
    public String[] findOcurrences(String text, String first, String second) {
        List<String> list = new ArrayList<>();
        String[] texts = text.split(" ");
        int n= texts.length;
        for(int i =0;i<n;i++){
            if(texts[i].equals(first) && i+1<n && texts[i+1].equals(second)&&i+2<n){
                list.add(texts[i+2]);
            }
        }
        int count = list.size();
        // if(count==0){
        //     return null;
        // }
        String[] ans = new String[count];
        for(int i = 0;i<count;i++){
            ans[i] = list.get(i);
        }
        return ans;

    }
}
