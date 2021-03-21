import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * @author WuChao
 * @since 2021/3/21 上午9:18
 */
public class _41 {
    /**
     * 剑指 Offer 41. 数据流中的中位数
     * 难度: hard
     * <p>
     * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
     * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
     *
     * 例如，
     *
     * [2,3,4] 的中位数是 3
     *
     * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
     *
     * 设计一个支持以下两种操作的数据结构：
     *
     * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
     * double findMedian() - 返回目前所有元素的中位数。
     *
     *
     * <p>
     * 示例：
     * 输入：
     * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
     * [[],[1],[2],[],[3],[]]
     * 输出：[null,null,null,1.50000,null,2.00000]
     *
     *
     * <p>
     * 数据范围：
     * 最多会对 addNum、findMedian 进行 50000 次调用。
     */

    /*
    正解在下面 MedianFinder2
     */
    class MedianFinder {

//        List<Integer> store;
        TreeMap<Integer, Integer> map;
        int count;
        /** initialize your data structure here. */
        public MedianFinder() {
//            this.store = new LinkedList<>();
            this.map = new TreeMap<>();
            this.count = 0;
        }

        public void addNum(int num) {
//            store.add(num);
            count++;

            map.put(num, map.getOrDefault(num, 0) + 1);

//            System.out.println(store);
        }

        public double findMedian() {
//            store.sort(Integer::compareTo); // 排序
            int medium = count / 2;
            int a=0, b = 0;


            if (count % 2 == 1) {
                return (double) b;
            }
            return (double)(a + b) / 2.0;

        }
    }

    /*
    使用堆来优化时间复杂度
    建立一个小顶堆A和一个大顶堆B，各保存列表的一半元素，且规定：
    A保存较大的一半，长度为N/2（N是偶数）或(N+1)/2(N是奇数)；
    B保存较小的一半，长度为N/2(N是偶数)或（N-1）/2(N是奇数)
    随后，中位数就可以由堆顶元素计算得到
     */
    class MedianFinder2 {
        Queue<Integer> A, B;    // A是小顶堆，保存较大的一半，B是大顶堆，存储较小的一半
        public  MedianFinder2() {
            this.A = new PriorityQueue<>();//小顶堆，保存较大的一半
            // 这里使用的是lambda表达式来表示堆的创建
            this.B = new PriorityQueue<>((x, y) -> (y - x));// 大顶堆，保存较小的一半
        }

        public void addNum(int num) {
            // 这一块直接使用Java中堆的定义来实现的，时间复制度是O(log n)
            if (A.size() != B.size()) {// A B 元素个数不同的时候，需要向B中添加一个元素，先向A中添加，再从A中弹出给B
                A.add(num);// 将新元素放入小顶堆，再把堆顶（最小值）给B中
                B.add(A.poll());
            } else {
                B.add(num);// A B 两堆中一样多的时候，先给B堆，再将B的堆顶元素推给A，保证A的元素数量比B多一个
                A.add(B.poll());
            }
        }
        public double findMedian(){
            return A.size() == B.size() ? (A.peek() + B.peek()) / 2.0 : A.peek();
        }
    }

    }
