package dataprocess;


import com.alibaba.fastjson.JSONObject;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @since 2021/4/29 下午2:39
 */
public class CutFieldandTokenizer {
    /**
     * 将原始数据中 56 个 取出类型为 text的域，分别按照 “doc_id,value”的类型写入对应的文件中
     */

    /**
     * 记录一下 text 类型的域名
     * "msg"
     * "tsm1_strategy"
     * "op_event"
     * "dir"
     * "lockFilePath"
     * "path"
     * "segment"
     * "tsm1_file"
     * "level"
     * "query"
     * "index"
     * "name="
     * "tsi1_partition"
     * "datatabase"
     * "name"
     * "fd"
     * "rp"
     * "LockFilePath"
     * "dst"
     * "op_name"
     * "error"
     * "elapsed"
     * "file"
     * "engine"
     * "src"
     * "target"
     * "caller"
     * "service"
     * "db"
     * "stmt"
     */


    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

        String path = "/home/charles/WorkSpace/ES实验/logs_trans/readJson";
        File file = new File(path);
        File[] listFiles = file.listFiles();
        BufferedReader reader = null;
        String outPath = "/home/charles/WorkSpace/ES实验/logs_trans/cutFields/";
        String[] fields = {"msg",
                "tsm1_strategy",
                "op_event",
                "dir",
                "lockFilePath",
                "path",
                "segment.path",
                "tsm1_file",
                "level",
                "query",
                "index",
                "name=",
                "tsi1_partition",
                "datatabase",
                "name",
                "fd",
                "rp",
                "LockFilePath",
                "dst",
                "op_name",
                "error",
                "elapsed",
                "file",
                "engine",
                "src",
                "target",
                "caller",
                "service",
                "db",
                "stmt"};

        System.out.println(fields.length);

        for (String field : fields) {
            int line = 1;
            for (int i = 0; i < 1; i++) {

                try {
                    StringBuffer sbf = new StringBuffer();
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(listFiles[i]), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
                    String temp = null;
                    while ((temp = reader.readLine()) != null) {
                        JSONObject jsonObject = JSONObject.parseObject(temp);
                        if (jsonObject.getString(field) != null) {
//                            System.out.println(line + "," + jsonObject.getString(field));
                            String temps = jsonObject.getString(field);
                            StandardAnalyzer analyzer = new StandardAnalyzer();
                            TokenStream tokenStream = analyzer.tokenStream("", temps);
                            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
                            tokenStream.reset();
                            String trans = "";
                            while (tokenStream.incrementToken()) {
                                trans += charTermAttribute.toString() + " ";
//                                System.out.print(charTermAttribute.toString()+" ");
                            }
                            tokenStream.close();

                            sbf.append(line + " " + trans + "\n");
                        }
                        line++;
                    }
                    reader.close();
                    File newFile = new File(outPath + field + ".txt");
                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }
                    // 获取该文件的缓冲输出流
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), StandardCharsets.UTF_8));
                    // 写入信息
                    bufferedWriter.write(sbf.toString());
                    bufferedWriter.flush();// 清空缓冲区
                    bufferedWriter.close();// 关闭输出流
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }




    }



}
