
package leetcode_everyday._2024.Jul;

import java.util.*;

/**
 * @author charles
 * @create 2024/7/20
 */
public class _15_721 {
    /**
     * 每日一题：2024/7/15
     * 721. 账户合并
     * https://leetcode.cn/problems/accounts-merge/description/?envType=daily-question&envId=2024-07-21
     */

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToIndex = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int emailCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, emailCount++);
                    emailToName.put(email, name);
                }

            }
        }
        UnionFind unionFind = new UnionFind(emailCount);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            Integer firstIndex = emailToIndex.get(firstEmail);
            for (int i = 2; i < account.size(); i++) {
                String nextEmail = account.get(i);
                Integer nextEmailIndex = emailToIndex.get(nextEmail);
                unionFind.union(firstIndex, nextEmailIndex);

            }
        }
        Map<Integer, List<String>> indexToEmail = new HashMap<>();
        for (String email : emailToIndex.keySet()) {
            int index = unionFind.find(emailToIndex.get(email));
            List<String> acconut = indexToEmail.getOrDefault(index, new ArrayList<>());
            acconut.add(email);
            indexToEmail.put(index, acconut);
        }
        List<List<String>> merged = new ArrayList<>();
        for (List<String> account : indexToEmail.values()) {
            Collections.sort(account);
            String name = emailToName.get(account.get(0));
            List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(name);
            mergedAccount.addAll(account);
            merged.add(mergedAccount);
        }
        return merged;



    }

    public class UnionFind{
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int index1, int index2) {
            parent[find(index2)] = find(index1);
        }

        public int find(int index) {
            if (parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
    }
}
