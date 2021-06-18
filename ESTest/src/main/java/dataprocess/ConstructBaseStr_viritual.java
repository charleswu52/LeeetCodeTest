package dataprocess;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/6/17 22:41
 */


public class ConstructBaseStr_viritual {
    public static void main(String[] args) throws Exception {
        String path = "E:\\研究生学习\\ES测试\\levelFields";
        String outPath = "E:\\研究生学习\\ES测试\\output\\";


        File file = new File(path);
        File[] listFiles = file.listFiles();
        BufferedReader reader = null;
        for (int i = 0; i < listFiles.length; i++) {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(listFiles[i]), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
            String temp = null;
            LinkedHashMap<String, LinkedHashMap<String, Integer>> store = new LinkedHashMap<>();
            String fileName = listFiles[i].getName();
            String finalStr = "{";
            while ((temp = reader.readLine()) != null) {

                // TODO 问题在这
//                JSONObject jsonObject = JSONObject.parseObject(temp);
                // Solution
                LinkedHashMap<String, Object> stringObjectLinkedHashMap = JSONObject.parseObject(temp.toString(), new TypeReference<LinkedHashMap<String, Object>>() {
                });
                Iterator<Map.Entry<String, Object>> iterator = stringObjectLinkedHashMap.entrySet().iterator();

//                Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> next = iterator.next();
                    String field = next.getKey();
                    String value = next.getValue().toString();
                    /*if (!store.containsKey(field)) {
                        store.put(field, new LinkedHashMap<String, Integer>() {{
                            put(value, 1);
                        }});
                    } else {
                        store.get(field).put(value, store.get(field).getOrDefault(value, 0) + 1);
                    }*/
                    if (!store.containsKey(field)) {
                        store.put(field, new LinkedHashMap<String, Integer>() {{
                            put(value, 1);
                        }});
                    } else if(!store.get(field).containsKey(value)){
                        store.get(field).put(value, 1);//store.get(field).put(value,1)
                    }else {
                        Integer t = store.get(field).get(value);
                        store.get(field).put(value, t+1);
                    }
                }
            }
            reader.close();
            Iterator<Map.Entry<String, LinkedHashMap<String, Integer>>> iterator = store.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, LinkedHashMap<String, Integer>> next = iterator.next();
                String field = next.getKey();
                int maxLen = Integer.MIN_VALUE;
                String maxStr = "";
                Iterator<Map.Entry<String, Integer>> iterator1 = next.getValue().entrySet().iterator();
                while (iterator1.hasNext()) {
                    Map.Entry<String, Integer> next1 = iterator1.next();
                    if (next1.getValue() > maxLen) {
                        maxLen = next1.getValue();
                        maxStr = next1.getKey();
                    }
                }
                if (iterator.hasNext()) {
                    finalStr += "\"" + field + "\":\"" + maxStr + "\",";
                } else {
                    finalStr += "\"" + field + "\":\"" + maxStr + "\"";

                }


            }
            finalStr += "}";

            File newFile = new File(outPath +"constructStr.txt");
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            // 获取该文件的缓冲输出流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
            // 写入信息
            bufferedWriter.write("fileName: "+fileName+ "\nBaseStr: " + finalStr+"\n");
            bufferedWriter.flush();// 清空缓冲区
            bufferedWriter.close();// 关闭输出流


        }


    }
}
