package dataprocess;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @since 2021/5/16 16:09
 */
public class ExtCharacter {
    public static void main(String[] args) throws Exception {
        String path = "E:\\WorkSpace\\Idea\\CodingStudy\\ESTest\\resources";
        File file = new File(path);
        File[] tempList = file.listFiles();
        BufferedReader reader = null;
        Set<Character> res = new HashSet<>();
        for (int i = 0; i < 1; i++) {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i]), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
            int temp;
            while ((temp = reader.read()) != -1) {
//                System.out.println((char) temp);
                res.add((char) temp);
            }
            reader.close();

        }
//        System.out.println(res);
        for (Character character : res) {
            System.out.println(character);
        }
//        int size = res.size();
//        System.out.println(size);
        Set<Integer> set = new HashSet<>();


    }
}

