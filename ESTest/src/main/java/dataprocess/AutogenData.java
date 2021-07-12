package dataprocess;

import java.io.*;
import java.net.URL;

/**
 * @author WuChao
 * @create 2021/7/12 15:34
 */
public class AutogenData {
    public static void main(String[] args)throws Exception {
        URL resource = AutogenData.class.getResource("/query.txt");
        System.out.println(resource);
        File origin = new File(resource.getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(origin)));// , "UTF-8"

        String line = null;
        int number = 165201;
        while ((line = reader.readLine()) != null && number < 165201 + 200) {
            String[] s = line.split(" ");
            s[0] = String.valueOf(number);
            number++;
            String outFile = AutogenData.class.getResource("/query2.txt").getPath();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile, true)));
            for (String t : s) {
                out.write(t+" ");
            }
            out.write("\n");
            out.close();
        }
        reader.close();
    }
}
