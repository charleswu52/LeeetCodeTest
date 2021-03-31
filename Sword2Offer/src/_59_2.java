import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/30 上午8:34
 */
public class _59_2 {
    /**
     * 剑指 Offer 59 - II. 队列的最大值
     * 难度: medium
     * <p>
     * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
     * 若队列为空，pop_front 和 max_value 需要返回 -1
     *
     * <p>
     * 例如：
     * 输入:
     * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
     * [[],[1],[2],[],[],[]]
     * 输出: [null,null,null,2,1,2]
     *
     * <p>
     * 数据范围：
     * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
     * 1 <= value <= 10^5
     */

    class MaxQueue {

        private List<Integer> numList;
        private List<Integer> maxList;

        public MaxQueue() {
            this.numList = new ArrayList<>();
            this.maxList = new ArrayList<>();
        }

        public int max_value() {
            return this.maxList.size() > 0 ? maxList.get(0): -1;
        }

        public void push_back(int value) {
            numList.add(value);
            if (maxList.size() < 1) {
                maxList.add(value);
            } else {
                if (maxList.get(maxList.size() - 1) >= value) {
                    maxList.add(value);
                } else {
                    for (int i = maxList.size()-1; i >=0 && value>maxList.get(i) ; i--) {
                        maxList.set(i, value);
                    }
                    maxList.add(value);
                }
            }
        }

        public int pop_front() {
            int ans = -1;
            if (numList.size() > 0) {
                ans = numList.get(0);
                numList.remove(0);
                maxList.remove(0);
            }
            return ans;
        }
    }

}
