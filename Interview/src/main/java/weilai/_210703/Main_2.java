package weilai._210703;

public class Main_2 {
    public int minNumberInRotateArray(int [] array) {
        int len = array.length;
        if (len == 0) {
            return 0;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return array[i + 1];
            }
        }
        return array[0];


    }
}
