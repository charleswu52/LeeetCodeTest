package leetcodetest.Apr;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @since 2021/4/4 上午8:35
 */
public class _4 {
    /**
     * 每日一题：2021/4/4
     * 781. 森林中的兔子
     * 难度: medium
     * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
     * 返回森林中兔子的最少数量。
     * <p>
     * 示例：
     * 输入: answers = [1, 1, 2]
     * 输出: 5
     * 解释:
     * 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
     * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
     * 设回答了 "2" 的兔子为蓝色。
     * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
     * 因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
     * <p>
     * 输入: answers = [10, 10, 10]
     * 输出: 11
     * <p>
     * 输入: answers = []
     * 输出: 0
     *
     * <p>
     * 数据范围：
     * answers 的长度最大为1000。
     * answers[i] 是在 [0, 999] 范围内的整数。
     */

    /*
    思路1:
    1.遍历answer数组，统计不同回答数出现的次数，这里使用HashMap来存储
    2.遍历HashMax，其中key是声明的右多少与其同样颜色的兔子，因此用声明的兔子数量即value来除以（key+1）来求有几对，这里是对结果向上取整
      结果乘以(key+1)就得到了当前key下兔子数量，累加所有的即得到所有的兔子数
     */
    public int numRabbits(int[] answers) {
        if (answers.length < 1) {
            return 0;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : answers) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            int key = entry.getKey();
            if (key == 0) {
                ans += entry.getValue();
            } else {
                int mod = entry.getKey() + 1;
                int s = entry.getValue() / mod;
                if (entry.getValue() % mod == 0) {
                    ans += s * mod;
                } else {
                    ans += (s + 1) * mod;
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int[] answers = {0,0,1,1,1};
        System.out.println(numRabbits(answers));

    }
}
