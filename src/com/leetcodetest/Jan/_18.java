package com.leetcodetest.Jan;

import java.util.*;

public class _18 {
    /**
     * 每日一题：2021/1/18
     * 721. 账户合并
     * 难度： medium
     * 给定一个列表 accounts，每个元素 accounts[i]是一个字符串列表，其中第一个元素 accounts[i][0]是名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
     * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
     * 请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
     *
     * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
     * 示例：
     * 输入：
     * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
     *             ["John", "johnnybravo@mail.com"],
     *             ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
     *             ["Mary", "mary@mail.com"]]
     * 输出：
     * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
     *  ["John", "johnnybravo@mail.com"],
     *  ["Mary", "mary@mail.com"]]
     * 解释：
     * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
     * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
     * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
     * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
     */

    /*
    思路：哈希表+并查集
    两个账户需要合并，当且仅当两个账户至少有一个共同的邮箱地址，因此这道题的实质是判断所有的邮箱地址中有哪些邮箱地址必定属于同一人，可以使用并查集实现。
    为了使用并查集实现账户合并，需要知道一共有多少个不同的邮箱地址，以及每个邮箱对应的名称，
    因此需要使用两个哈希表分别记录每个邮箱对应的编号和每个邮箱对应的名称，遍历所有的账户并在两个哈希表中记录相应的信息。
    虽然同一个邮箱地址可能在多个账户中出现，但是同一个邮箱地址在两个哈希表中都只能存储一次。

    然后使用并查集进行合并操作。由于同一个账户中的邮箱地址一定属于同一个人，因此遍历每个账户，对账户中的邮箱地址进行合并操作。
    并查集存储的是每个邮箱地址对应的编号，合并操作也是针对编号进行合并。
    完成并查集的合并操作之后，即可知道合并后有多少个不同的账户。
    遍历所有的邮箱地址，对于每个邮箱地址，通过并查集得到该邮箱地址属于哪个合并后的账户，即可整理出每个合并后的账户包含哪些邮箱地址。
    对于每个合并后的账户，需要整理出题目要求的返回账户的格式，具体做法是：
    将邮箱地址排序，账户的名称可以通过在哈希表中查找任意一个邮箱对应的名称得到，将名称和排序后的邮箱地址整理成一个账户列表。
    对所有合并后的账户整理出账户列表，即可得到最终答案。
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToIndex = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int emailCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
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
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                unionFind.union(firstIndex, nextIndex);
            }
        }
        Map<Integer, List<String>> indexToEamils = new HashMap<>();
        for (String email : emailToIndex.keySet()) {
            int index = unionFind.find(emailToIndex.get(email));
            List<String> account = indexToEamils.getOrDefault(index, new ArrayList<String>());
            account.add(email);
            indexToEamils.put(index, account);
        }
        List<List<String>> merged = new ArrayList<>();
        for (List<String> emails : indexToEamils.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }
        return merged;

    }

    public void _21_1_18(){
        List<List<String>> accounts = new ArrayList<>();
        List<String> temp1 = new ArrayList<>();
        temp1.add("John");
        temp1.add("johnsmith@mail.com");
        temp1.add("john00@mail.com");
        accounts.add(temp1);
        List<String> temp2 = new ArrayList<>();

        temp2.add("John");
        temp2.add("johnnybravo@mail.com");
        accounts.add(temp2);

        List<String> temp3 = new ArrayList<>();
        temp3.add("John");
        temp3.add("johnsmith@mail.com");
        temp3.add("john_newyork@mail.com");
        accounts.add(temp3);

        List<String> temp4 = new ArrayList<>();
        temp4.add("Mary");
        temp4.add("mary@mail.com");
        accounts.add(temp4);

        List<List<String>> res = accountsMerge(accounts);
        for (int i = 0; i < res.size(); i++) {
            for (int j = 0; j < res.get(i).size(); j++) {
                System.out.print(res.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }

    /**
     * 并查集的定义
     */
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
