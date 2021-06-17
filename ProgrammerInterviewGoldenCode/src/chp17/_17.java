package chp17;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/6/9 上午10:12
 */
public class _17 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.17. 多次搜索
     * 难度: medium
     * <p>
     *  给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。
     *  输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
     * <p>
     * 示例:
     * 输入：
     * big = "mississippi"
     * smalls = ["is","ppi","hi","sis","i","ssippi"]
     * 输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
     * <p>
     * 数据范围：
     * 0 <= len(big) <= 1000
     * 0 <= len(smalls[i]) <= 1000
     * smalls的总字符数不会超过 100000。
     * 你可以认为smalls中没有重复字符串。
     * 所有出现的字符均为英文小写字母。
     */

    /*
    思路1： 根据small中的单词对big进行切分 然后遍历计算位置 -》 暂无AC
     */
    public int[][] multiSearch(String big, String[] smalls) {
        if ("".equals(big) ||(smalls.length == 1 && "".equals(smalls[0]))) {
            int[][] ans = new int[smalls.length][];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = new int[]{};
            }
            return ans;
        }

        List<List<Integer>> res = new ArrayList<>();
        for (String small : smalls) {
            boolean contains = big.contains(small);
            List<Integer> integers = new ArrayList<>();
            if (contains) {
                String[] split = big.split(small);
                int idx = 0;
                if (big.startsWith(small)) {
                    integers.add(0);
                    idx += small.length();
                }
                for (String t : split) {
                    idx += t.length();
                    if (idx < big.length()) {
                        integers.add(idx);
                    }
                    idx += small.length();
                }
                res.add(integers);
            } else {
                res.add(integers);
            }

        }
        int[][] ans = new int[res.size()][];
        int i = 0;
        for (List<Integer> list : res) {
            ans[i++] = list.stream().mapToInt(Integer::valueOf).toArray();
        }
        return ans;

    }

    /*
    思路2： 使用前缀树 -》 AC
     */

    class Trie{
        TrieNode root;

        public Trie(String[] words) {
            root = new TrieNode();
            for (String word : words) {
                TrieNode node = root;
                for (char w : word.toCharArray()) {
                    int i = w - 'a';
                    if (node.next[i] == null) {
                        node.next[i] = new TrieNode();
                    }
                    node = node.next[i];
                }
                node.end = word;
            }
        }

        public List<String> search(String str) {
            TrieNode node = root;
            List<String> res = new ArrayList<>();
            for (char ch : str.toCharArray()) {
                int i = ch - 'a';
                if (node.next[i] == null) {
                    break;
                }
                node = node.next[i];
                if (node.end != null) {
                    res.add(node.end);
                }
            }
            return res;
        }
    }
    class TrieNode{
        String end;
        TrieNode[] next = new TrieNode[26];
    }


    /*
    使用前缀树进行多次搜索
     */
    public int[][] multiSearch2(String big, String[] smalls) {
        Trie trie = new Trie(smalls);
        Map<String, List<Integer>> hit = new HashMap<>();
        for (int i = 0; i < big.length(); i++) {
            List<String> matchs = trie.search(big.substring(i));
            for (String word : matchs) {
                if (!hit.containsKey(word)) {
                    hit.put(word, new ArrayList<>());
                }
                hit.get(word).add(i);
            }
        }

        int[][] res = new int[smalls.length][];
        for (int i = 0; i < smalls.length; i++) {
            List<Integer> list = hit.get(smalls[i]);
            if (list == null) {
                res[i] = new int[0];
                continue;
            }
            int size = list.size();
            res[i] = new int[size];
            for (int j = 0; j < size; j++) {
                res[i][j] = list.get(j);
            }
        }
        return res;

    }



        @Test
    public void test() {
        String big = "mississippi";
        String[] smalls = {"is","ppi","hi","sis","i","ssippi"};
        System.out.println(Arrays.deepToString(multiSearch(big, smalls)));
    }
}
