package dataprocess;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/6/8 下午3:51
 */
public class CountField {


    public static void main(String[] args) {
        String path = "/home/charles/WorkSpace/ES实验/logs_trans/readJson/";
        String outPath = "/home/charles/WorkSpace/ES实验/logs_trans/fields/";

        File file = new File(path);
        File[] listFiles = file.listFiles();
        BufferedReader reader = null;
        int line = 1;
        Set<String> fileds = new HashSet<>();
        for (int i = 0; i < listFiles.length; i++) {

            try {
//                StringBuffer sbf = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(listFiles[i]), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
                String temp = null;
                while ((temp = reader.readLine()) != null) {
                    JSONObject jsonObject = JSONObject.parseObject(temp);
                    Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, Object> next = iterator.next();
                        String key = next.getKey();
                        String value = next.getValue().toString();
//                        System.out.println(key);
                        fileds.add(key);
                        File newFile = new File(outPath + key + ".txt");
                        if (!newFile.exists()) {
                            newFile.createNewFile();
                        }
                        // 获取该文件的缓冲输出流
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile,true), StandardCharsets.UTF_8));
                        // 写入信息
                        bufferedWriter.write(value+"\n");
                        bufferedWriter.flush();// 清空缓冲区
                        bufferedWriter.close();// 关闭输出流
                    }
//                    if (jsonObject.getString(field) != null) {
////                            System.out.println(line + "," + jsonObject.getString(field));
//                        sbf.append(line + " " + jsonObject.getString(field) + "\n");
//                    }
                    line++;
                }
                reader.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println(line);
        System.out.println(fileds.size());
        for (String s : fileds) {
            System.out.println(s);
        }
    }
}
