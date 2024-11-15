package leetcode_everyday._2021.Jan;

public class _31 {
    /**
     * 每日一题：2021/1/31
     * 839. 相似字符串组
     * 难度: hard
     * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
     * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)；"rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
     *
     * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。
     * 形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
     *
     * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
     * <p>
     * 示例：
     * 输入：strs = ["tars","rats","arts","star"]
     * 输出：2
     * <p>
     * 数据范围：
     * 1 <= strs.length <= 100
     * 1 <= strs[i].length <= 1000
     * sum(strs[i].length) <= 2 * 104
     * strs[i] 只包含小写字母。
     * strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。
     */



    /*
    并查集月最后一题
    我们把每一个字符串看作点，字符串之间是否相似看作边，那么可以发现本题询问的是给定的图中有多少连通分量。
    于是可以想到使用并查集维护节点间的连通性。

    我们枚举给定序列中的任意一对字符串，检查其是否具有相似性，如果相似，那么我们就将这对字符串相连。
    在实际代码中，我们可以首先判断当前这对字符串是否已经连通，如果没有连通，我们再检查它们是否具有相似性，可以优化一定的时间复杂度的常数。
     */

    /**
     * 并查集的解决方法
     *
     * @param strs
     * @return
     */
    int[] f; // 父节点

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int fi = find(i), fj = find(j);
                if (fi == fj) {
                    continue;
                }
                if (check(strs[i], strs[j], m)) {
                    f[fi] = fj;

                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] == i) {
                ans++;
            }
        }
        return ans;
    }

    public int find(int x) {
        if (f[x] == x) {
            return x;
        } else {
            return find(f[x]);
        }
    }

    public boolean check(String a, String b, int len) {
        int num = 0;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                num++;
                if (num > 2) {
                    return false;
                }
            }
        }
        return true;
    }

    public void _21_1_31() {
        String[] strs = {"tars", "rats", "arts", "star"};
        System.out.println(numSimilarGroups(strs));

    }
}
