/**
 * @author WuChao
 * @create 2021/7/27 13:48
 */
public class Random {

    int N = 7;
    int M = 10;
    int a = 4;

    public int randN() {
        int x = (randM() - 1) * M + randM();
        return x <= N * a ? x % N + 1 : randN(); //这里a是使得调用次数最少的一个系数
    }

    public int randM() {
        // randM 的实现是已经给出的
        return 1;

    }
}


