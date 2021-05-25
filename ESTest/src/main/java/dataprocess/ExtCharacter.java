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
        String path = "/media/charles/Data/WorkSpace/Idea/CodingStudy/ESTest/resources/";
        File file = new File(path);
        File[] tempList = file.listFiles();
        BufferedReader reader = null;
        Set<String> res = new HashSet<>();
        for (int i = 0; i < 1; i++) {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i]), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
            String temp;
            while ((temp = reader.readLine()) != null) {
//                System.out.println((char) temp);
                String[] s = temp.split(" ");
                for (String s1 : s) {
                    res.add(s1);
                }
            }
            reader.close();

        }
//        System.out.println(res);
//        for (String character : res) {
//            System.out.println(character);
//        }
//        int size = res.size();
        System.out.println(res.size());



    }
}

