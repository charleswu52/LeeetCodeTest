package leetcode_everyday._2022.Jun;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2022/6/29 9:33
 */
public class _29_535 {
    /**
     * 每日一题：2022/6/29
     * <p>
     * 535. TinyURL 的加密与解密
     * <p>
     * 难度：medium
     * <p>
     * TinyURL 是一种 URL 简化服务， 比如：当你输入一个 URL https://leetcode.com/problems/design-tinyurl 时，
     * 它将返回一个简化的URL http://tinyurl.com/4e9iAk 。请你设计一个类来加密与解密 TinyURL 。
     * <p>
     * 加密和解密算法如何设计和运作是没有限制的，你只需要保证一个 URL 可以被加密成一个 TinyURL ，
     * 并且这个 TinyURL 可以用解密方法恢复成原本的 URL 。
     * <p>
     * 实现 Solution 类：
     * <p>
     * Solution() 初始化 TinyURL 系统对象。
     * String encode(String longUrl) 返回 longUrl 对应的 TinyURL 。
     * String decode(String shortUrl) 返回 shortUrl 原本的 URL 。题目数据保证给定的 shortUrl 是由同一个系统对象加密的。
     * <p>
     * 示例
     * <p>
     * 输入：url = "https://leetcode.com/problems/design-tinyurl"
     * <p>
     * 输出："https://leetcode.com/problems/design-tinyurl"
     * <p>
     * 解释：
     * Solution obj = new Solution();
     * string tiny = obj.encode(url); // 返回加密后得到的 TinyURL 。
     * string ans = obj.decode(tiny); // 返回解密后得到的原本的 URL 。
     * <p>
     * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
     * <p>
     * 范围
     * <p>
     * 1 <= url.length <= 10^4
     * 题目数据保证 url 是一个有效的 URL
     */

    /*
    思路：
     */
    public class Codec {
        private int id=0;
        private Map<Integer, String> map = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            id++;
            map.put(id, longUrl);
            return "http://tinyurl.com/" + id;

        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            int p = shortUrl.lastIndexOf('/') + 1;
            int key = Integer.parseInt(shortUrl.substring(p));
            return map.getOrDefault(key, null);

        }
    }

}
