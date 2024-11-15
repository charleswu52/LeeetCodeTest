package dataprocess;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/7/18 12:47
 */
public class LevelCutFields16File {

    public static void main(String[] args) {
        String path = "E:\\研究生学习\\ES测试\\ES实验\\logs\\";
        String outPath = "E:\\研究生学习\\ES测试\\ES实验\\logs_trans\\all16LeveCut\\";

        File file = new File(path);
        File[] listFiles = file.listFiles();
        System.out.println(listFiles.length);
        BufferedReader reader = null;
        Set<String> fileds = new HashSet<>();
        for (int i = 0; i < listFiles.length; i++) {
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(listFiles[i]), StandardCharsets.UTF_16));//new BufferedReader(new FileReader());
                String temp = null;
                System.out.println(listFiles[i].getName());

                while ((temp = reader.readLine()) != null) {
                    JSONObject jsonObject = JSONObject.parseObject(temp);

                    String caller = (String) jsonObject.get("caller");
                    int i1 = caller.hashCode();
//                    System.out.println(caller);
                    fileds.add((String) caller);
                    File newFile = new File(outPath + listFiles[i].getName() + "\\" + i1 + ".txt");
                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }
                    // 获取该文件的缓冲输出流
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                    // 写入信息
                    bufferedWriter.write(temp + "\n");
                    bufferedWriter.flush();// 清空缓冲区
                    bufferedWriter.close();// 关闭输出流
//                    Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
//                    while (iterator.hasNext()) {
//                        Map.Entry<String, Object> next = iterator.next();
//                        String key = next.getKey();
////                        String value = next.getValue().toString();
//                        System.out.println(key);
////                        fileds.add(key);
////
//                    }
//                    if (jsonObject.getString(field) != null) {
////                            System.out.println(line + "," + jsonObject.getString(field));
//                        sbf.append(line + " " + jsonObject.getString(field) + "\n");
//                    }
                }
                reader.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
////        System.out.println(line);
//        System.out.println(fileds.size());
//        for (String s : fileds) {
//            System.out.println(s);
//        }
    }
}
