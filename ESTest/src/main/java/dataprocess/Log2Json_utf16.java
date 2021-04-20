package dataprocess;

import java.io.*;

/**
 * @author WuChao
 * @since 2021/4/16 下午1:01
 */
public class Log2Json_utf16 {
    // 原始log(UTF 16) => UTF8格式的log => 导入json
    public static void main(String[] args) {
        String path = "/home/charles/WorkSpace/ES实验/logs";
        File file = new File(path);
        File[] tempList = file.listFiles();
        BufferedReader reader = null;
        String outPath = "/home/charles/WorkSpace/ES实验/logs_trans/1/";
        int line = 1;
        for (int i = 0; i < 1; i++) {
            try {
                StringBuffer sbf = new StringBuffer();
                StringBuffer sbf2 = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i]), "UTF-16"));//new BufferedReader(new FileReader());
                String temp=null;
                while ((temp = reader.readLine()) != null) {
                    if (line < 165200 / 2) {
                        sbf.append("{\"index\":{\"_index\":\"log_qlain\",\"_type\":\"_doc\",\"_id\":").append(line++).append("}}\n");

                        sbf.append(temp).append("\n");
                    } else {
                        sbf2.append("{\"index\":{\"_index\":\"log_qlain\",\"_type\":\"_doc\",\"_id\":").append(line++).append("}}\n");

                        sbf2.append(temp).append("\n");
                    }
                }
                reader.close();
//                File newFile =new File(outPath+"log_plain_"+tempList[i].getName()+".json");
                File newFile =new File(outPath+"log_utf16_"+tempList[i].getName()+"1.json");
                File newFile2 =new File(outPath+"log_utf16_"+tempList[i].getName()+"2.json");
//                File newFile =new File(outPath+"log8_1_ngram_"+i+".json");
//                BufferedWriter out = new BufferedWriter(new FileWriter(outPath+tempList[i].getName()+".json"));
                //true = append file
                if(!newFile.exists()){
                    newFile.createNewFile();
                }
                if(!newFile2.exists()){
                    newFile2.createNewFile();
                }
                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF-16"));
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile2), "UTF-16"));
                // 写入信息
                bufferedWriter.write(sbf.toString());
                bufferedWriter.flush();// 清空缓冲区
                bufferedWriter.close();// 关闭输出流

                // 写入信息
                bufferedWriter2.write(sbf2.toString());
                bufferedWriter2.flush();// 清空缓冲区
                bufferedWriter2.close();// 关闭输出流
//                System.out.println(tempList[i].getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
