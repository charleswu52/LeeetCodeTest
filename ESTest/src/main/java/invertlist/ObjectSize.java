package invertlist;

import org.apache.lucene.util.Accountable;
import org.apache.lucene.util.RamUsageEstimator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/11/4 13:30
 */
public class ObjectSize {

    static TreeNode root;
    static long size = 0L;
    public static void main(String[] args) {
        /*计算一个对象在内存占用的空间
        HashMap<String, List<Integer>> stringListHashMap = new HashMap<>();

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            stringList.add(String.valueOf(i));
            stringListHashMap.put(String.valueOf(i), new ArrayList<>());
        }
        for (int i = 0; i < 1000000; i++) {
            stringList.add(String.valueOf(i));
            stringListHashMap.put(String.valueOf(i), new ArrayList<>());
        }
        String a = "2";
        int n = 100000000;
        // 计算一个对象在内存中的大小
        System.out.println(RamUsageEstimator.humanReadableUnits(RamUsageEstimator.sizeOfObject(stringList)));
        System.out.println(RamUsageEstimator.humanReadableUnits(RamUsageEstimator.sizeOfObject(stringListHashMap)));


        long maxValue = Long.MAX_VALUE;
        long minValue = Long.MIN_VALUE;
        long c = maxValue + 1L;
        System.out.println(maxValue);
        System.out.println(RamUsageEstimator.humanReadableUnits(maxValue));
        System.out.println(minValue);
        System.out.println(c);

         */

        bulidTres();
        dfsCount(root);
        System.out.println(RamUsageEstimator.humanReadableUnits(size));


    }

    /**
     * 二叉树节点定义
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        List<Integer> list;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void bulidTres() {
        root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        node1.list = new ArrayList<>();
        node1.list.add(3);
        node1.list.add(4);
        node1.list.add(5);
        node1.list.add(6);
        TreeNode node2 = new TreeNode(3);
        node2.list = new LinkedList<>();
        node2.list.add(2);
        node2.list.add(5);
        node2.list.add(8);
//        node2.list.add(2);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

    }

    public static void dfsCount(TreeNode node) {
        if (node == null) {
            return;
        }
//        String temp = RamUsageEstimator.humanSizeOf(node);
        long temp = RamUsageEstimator.sizeOf((Accountable) node);
        System.out.println(node.val);
        System.out.println(RamUsageEstimator.humanReadableUnits(temp));
        size += temp;
        dfsCount(node.left);
        dfsCount(node.right);
    }


}
