package chp3;

import java.util.LinkedList;

/**
 * @author WuChao
 * @since 2021/4/10 下午4:09
 */
public class _6 {
    /**
     * 程序员面试金典(version 6) - 面试题 03.06. 动物收容所
     * 难度: easy
     * <p>
     * 动物收容所。有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。
     * 在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，
     * 或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。
     * 换言之，收养人不能自由挑选想收养的对象。请创建适用于这个系统的数据结构，实现各种操作方法，
     * 比如enqueue、dequeueAny、dequeueDog和dequeueCat。
     * 允许使用Java内置的LinkedList数据结构。
     * <p>
     * enqueue方法有一个animal参数，animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。
     * dequeue*方法返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]。
     * <p>
     * 例如：
     * 输入：
     * ["AnimalShelf", "enqueue", "enqueue", "enqueue", "dequeueDog", "dequeueCat", "dequeueAny"]
     * [[], [[0, 0]], [[1, 0]], [[2, 1]], [], [], []]
     * 输出：
     * [null,null,null,null,[2,1],[0,0],[1,0]]
     *
     *
     *
     * <p>
     * 说明：
     * 收纳所的最大容量为20000
     */


    class AnimalShelf {
        LinkedList<int[]> store;

        public AnimalShelf() {
            store = new LinkedList<>();

        }

        public void enqueue(int[] animal) {
            store.addLast(animal);

        }

        public int[] dequeueAny() {
            if (store.size() > 0) {

                return store.removeFirst();
            }
            return new int[]{-1, -1};
        }

        public int[] dequeueDog() {
            for (int i = 0; i < store.size(); i++) {
                if (store.get(i)[1] == 1) {
                    return store.remove(i);
                }
            }
            return new int[]{-1, -1};

        }

        public int[] dequeueCat() {
            for (int i = 0; i < store.size(); i++) {
                if (store.get(i)[1] == 0) {
                    return store.remove(i);
                }
            }
            return new int[]{-1, -1};

        }
    }
}
