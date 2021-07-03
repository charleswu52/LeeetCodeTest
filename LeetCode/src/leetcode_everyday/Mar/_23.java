package leetcode_everyday.Mar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/23 上午7:33
 */
public class _23 {
    /**
     * 每日一题：2021/3/23
     * 341. 扁平化嵌套列表迭代器
     * 难度: medium
     * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
     * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
     * <p>
     * 示例：
     * 输入: [[1,1],2,[1,1]]
     * 输出: [1,1,2,1,1]
     * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
     *
     *
     * <p>
     * 数据范围：
     */

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    /*
    做到这个题，需要去搜索学习一下Java的迭代器的使用 应该是在 集合 那块
     */

    /*
    嵌套的整型列表是一个树形结构，树上的叶子节点对应一个整数，非叶节点对应一个列表。
    在这棵树上深度优先搜索的顺序就是迭代器遍历的顺序。
    可以先遍历整个嵌套列表，将所有的整数存入一个数组，然后遍历该数组从而实现next 和 hasNext方法
     */

    public class NestedIterator implements Iterator<Integer> {

        private List<Integer> vals; // 存储数据
        private Iterator<Integer> cur; //迭代器


        public NestedIterator(List<NestedInteger> nestedList) {
            vals = new ArrayList<>();
            dfs(nestedList);
            cur = vals.iterator();
        }

        @Override
        public Integer next() {
            return cur.next();

        }

        @Override
        public boolean hasNext() {
            return cur.hasNext();

        }

        public void dfs(List<NestedInteger> nestedIntegerList) {
            for (NestedInteger nest : nestedIntegerList) {
                if (nest.isInteger()) {
                    vals.add(nest.getInteger());
                } else {
                    dfs(nest.getList());
                }
            }
        }
    }


}
