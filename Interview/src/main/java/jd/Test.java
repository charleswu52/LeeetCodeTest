package jd;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/7/21 20:38
 */
public class Test {
    private volatile static Test test;
    private Test(){

    }
    public static Test getTest(){
        if (test == null) {
            synchronized (Test.class) {
                if (test == null) {
                    test = new Test();
                }
            }
        }
        return test;
    }

    public static  void maopao(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
//            boolean isSorted = true;
            for (int j = i + 1; j < n; j++) {
                if (array[i] > array[j]) {
//                    isSorted = false;
                    int t = array[i];
                    array[i] = array[j];
                    array[j] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 1, 2, 4, 6};
        maopao(array);
        System.out.println(Arrays.toString(array));
    }
}
