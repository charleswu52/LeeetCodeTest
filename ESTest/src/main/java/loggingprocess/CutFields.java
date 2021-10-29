package loggingprocess;

import com.alibaba.fastjson.JSONObject;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @create 2021/10/28 19:48
 */
public class CutFields {
    public static void main(String[] args) throws Exception {
        String filePath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\input\\";
        File file = new File(filePath);

        File[] fileList = file.listFiles();
        BufferedReader reader = null;
        String outPath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\output\\";

        for (int i = 0; i < fileList.length; i++) {

            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileList[i]), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
            String temp = null;
            int id = 0;
            while ((temp = reader.readLine()) != null) {
                id++;
                JSONObject jsonObject = JSONObject.parseObject(temp);
                Object value = jsonObject.get("request");
                StandardAnalyzer analyzer = new StandardAnalyzer();
                TokenStream tokenStream = analyzer.tokenStream("", value.toString());
                CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
                tokenStream.reset();
                String trans = "";
                while (tokenStream.incrementToken()) {
                    trans += charTermAttribute.toString()+" ";
                }
                tokenStream.close();

                File newFile = new File(outPath + "request.txt");
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                // 写入信息
                bufferedWriter.write(id+":"+trans + "\n");
                bufferedWriter.flush();// 清空缓冲区
                bufferedWriter.close();// 关闭输出流

            }

        }
        reader.close();

    }
}


    /*
    for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();

                        StandardAnalyzer analyzer = new StandardAnalyzer();
                        TokenStream tokenStream = analyzer.tokenStream("", value.toString());
                        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
                        tokenStream.reset();
                        String trans = "";
                        while (tokenStream.incrementToken()) {
                            trans += charTermAttribute.toString();
                        }
                        tokenStream.close();

                        File newFile = new File(outPath + key + ".txt");
                        if (!newFile.exists()) {
                            newFile.createNewFile();
                        }
                        // 获取该文件的缓冲输出流
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                        // 写入信息
                        bufferedWriter.write(trans + "\n");
                        bufferedWriter.flush();// 清空缓冲区
                        bufferedWriter.close();// 关闭输出流

                    }
     */

