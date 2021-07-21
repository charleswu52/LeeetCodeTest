package dataprocess;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

/**
 * @author WuChao
 * @create 2021/7/21 9:41
 */
public class AllQueryFieldsFile {
    public static void main(String[] args) throws Exception {
        String path = "E:\\研究生学习\\ES测试\\ES实验\\logs\\";
        File file = new File(path);
        File[] tempList = file.listFiles();
        BufferedReader reader = null;
        String outPath = "E:\\研究生学习\\ES测试\\ES实验\\logs_trans\\queryFile\\";

        int maxLen = Integer.MIN_VALUE;
        String maxStr = "";
        LinkedHashMap<String, Integer> stringCount = new LinkedHashMap<>();

        for (int i = 0; i < tempList.length; i++) {
            try {
                StringBuffer sbf = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i]), StandardCharsets.UTF_16));//new BufferedReader(new FileReader());
                String temp = null;
                while ((temp = reader.readLine()) != null) {
                    JSONObject jsonObject = JSONObject.parseObject(temp);
                    String query = jsonObject.getString("query");
                    if (query != null) {
//                        sbf.append(query + "\n");
                        stringCount.put(query, stringCount.getOrDefault(query, 0) + 1);

                        if (stringCount.get(query) > maxLen) {
                            maxStr = query;
                            maxLen = stringCount.get(query);
                        }
                    }
                }
                reader.close();
                /*
                File newFile =new File(outPath+"allQuery.txt");
                if(!newFile.exists()){
                    newFile.createNewFile();
                }
                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile,true), StandardCharsets.UTF_8));
                // 写入信息
                bufferedWriter.write(sbf.toString());
                bufferedWriter.flush();// 清空缓冲区
                bufferedWriter.close();// 关闭输出流

                 */
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(maxLen);
        System.out.println(maxStr);
    }

    @Test
    public void test() {


    }
}
