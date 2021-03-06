package dataprocess;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @since 2021/4/21 上午10:53
 */
public class Utf16to8 {
    // 将utf-16 格式的文件转为8
    public static void main(String[] args) {
        String path = "/home/charles/WorkSpace/ES实验/logs";
        File file = new File(path);
        File[] tempList = file.listFiles();
        BufferedReader reader = null;
        String outPath = "/home/charles/WorkSpace/ES实验/utf8/";
//        int line = 1;
        for (int i = 0; i < 1; i++) {
            try {
                StringBuffer sbf = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i]), StandardCharsets.UTF_16));//new BufferedReader(new FileReader());
                String temp=null;
                while ((temp = reader.readLine()) != null) {
//                    sbf.append("{\"index\":{\"_index\":\"log_qlain\",\"_type\":\"_doc\",\"_id\":" + (line++)+"}}\n");
                    sbf.append(temp+"\n");
                }
                reader.close();
                File newFile =new File(outPath+tempList[i].getName()+".json");
                if(!newFile.exists()){
                    newFile.createNewFile();
                }
                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), StandardCharsets.UTF_8));
                // 写入信息
                bufferedWriter.write(sbf.toString());
                bufferedWriter.flush();// 清空缓冲区
                bufferedWriter.close();// 关闭输出流
//                System.out.println(tempList[i].getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
