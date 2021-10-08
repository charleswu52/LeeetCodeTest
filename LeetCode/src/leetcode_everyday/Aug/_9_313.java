package leetcode_everyday.Aug;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WuChao
 * @create 2021/8/9 13:58
 */
public class _9_313 {
    /**
     * 每日一题：2021/8/9
     * 313. 超级丑数
     * 难度：medium
     * <p>
     * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
     *
     * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
     *
     * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
     *
     * <p>
     * 示例 1：
     * 输入：n = 12, primes = [2,7,13,19]
     * 输出：32
     * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
     *
     * 示例 2：
     * 输入：n = 1, primes = [2,3,5]
     * 输出：1
     * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
     * <p>
     * 注意:
     * 1 <= n <= 106
     * 1 <= primes.length <= 100
     * 2 <= primes[i] <= 1000
     * 题目数据 保证 primes[i] 是一个质数
     * primes 中的所有值都 互不相同 ，且按 递增顺序 排列
     */

    /*
    思路：动态规划
    类似之前 丑数I 的题目，只不过 丑数I 是每个数的质因子是2 3 5
    这里同样的也使用动态规划的算法，定义dp[i] 表示第i个超级丑数，第n个超级丑数即为 dp[n]
    由于最小的超级丑数是1，因此dp[1]=1
    如何得到其余的超级丑数呢？创建与数组 primes 相同长度的数组 pointers，表示下一个超级丑数是当前指针指向的超级丑数乘以对应的质因数。
    初始时，数组 pointers 的元素值都是 1。

    当 2≤i≤n 时，令 dp[i]= min(0≤j<m ) {dp[pointers[j]]×primes[j]}，然后对于每个 0≤j<m，分别比较 dp[i] 和
    dp[pointers[j]] × primes[j] 是否相等，如果相等则将 pointers[j] 加 1。

     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int m = primes.length;
        int[] pointers = new int[m];
        Arrays.fill(pointers, 1);
        for (int i = 2; i <= n; i++) {
            int[] nums = new int[m];
            int minNum = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                nums[j] = dp[pointers[j]] * primes[j];
                minNum = Math.min(minNum, nums[j]);
            }
            dp[i] = minNum;
            for (int j = 0; j < m; j++) {
                if (minNum == nums[j]) {
                    pointers[j]++;
                }
            }
        }
        return dp[n];
    }

    /**
     * 1000万的量 , 50个线程去修改
     * retry = 0 要3秒多
     * retry = 10 只需要1秒左右!!!
     */
    public static void main(String[] args) throws InterruptedException {
        final int capacity = 10000000;
        final int threadSum = 50;
        final int oneThreadDo = capacity / threadSum;

        int[] array = new int[capacity];
        AtomicBoolean[] casMap = new AtomicBoolean[capacity];
        for (int i = 0; i < capacity; i++) {
            casMap[i] = new AtomicBoolean(false);
        }
        Random random = new Random();
        ReentrantLock lock = new ReentrantLock();
        long startTime = System.currentTimeMillis();

        //////模拟一些初始化 ↑
        CountDownLatch countDownLatch = new CountDownLatch(threadSum);
        for (int i = 0; i < threadSum; i++) {
            new Thread(() -> {
                for (int j = 0; j < oneThreadDo; j++) {
                    int toUpdateIdx = random.nextInt(capacity);
                    AtomicBoolean atomicBoolean = casMap[toUpdateIdx];
                    int retry = 0;
                    boolean ok = false;
                    while (retry < 10) {
                        ok = atomicBoolean.compareAndSet(false, true);
                        if (ok)
                            break;
                        retry++;
                    }
                    if (ok) {
                        array[toUpdateIdx] += 1;
                        atomicBoolean.set(false);
                    } else {
                        lock.lock();
                        array[toUpdateIdx] += 1;
                        atomicBoolean.set(false);
                        lock.unlock();
                    }
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.err.println("执行完成 : " + (System.currentTimeMillis() - startTime));

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        System.err.println(sum);
    }
}
