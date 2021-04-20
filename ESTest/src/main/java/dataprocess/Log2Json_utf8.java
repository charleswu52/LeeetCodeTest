package dataprocess;

import java.io.*;

/**
 * @author WuChao
 * @since 2021/4/16 下午1:01
 */
public class Log2Json_utf8 {
    // 原始log(UTF 16) => UTF8格式的log => 导入json
    public static void main(String[] args) {
        String path = "/home/charles/WorkSpace/ES实验/logs_trans/utf8_log";
        File file = new File(path);
        File[] tempList = file.listFiles();
        BufferedReader reader = null;
        String outPath = "/home/charles/WorkSpace/ES实验/logs_trans/1/";
        int line = 1;
        for (int i = 0; i < 1; i++) {
            try {
                StringBuffer sbf = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i]), "UTF-8"));//new BufferedReader(new FileReader());
                String temp=null;
                while ((temp = reader.readLine()) != null) {
                    sbf.append("{\"index\":{\"_index\":\"log_qlain\",\"_type\":\"_doc\",\"_id\":" + (line++)+"}}\n");
                    sbf.append(temp+"\n");
                }
                reader.close();
//                File newFile =new File(outPath+"log_plain_"+tempList[i].getName()+".json");
                File newFile =new File(outPath+"log_utf8_"+tempList[i].getName()+".json");
//                File newFile =new File(outPath+"log8_1_ngram_"+i+".json");
//                BufferedWriter out = new BufferedWriter(new FileWriter(outPath+tempList[i].getName()+".json"));
                //true = append file
                if(!newFile.exists()){
                    newFile.createNewFile();
                }
                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8"));
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
