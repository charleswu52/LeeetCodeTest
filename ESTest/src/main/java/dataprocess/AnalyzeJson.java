package dataprocess;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/6/8 下午3:35
 */
public class AnalyzeJson {
    //获取json
    public static String getJson() {
        String jsonStr = "";
        try {
            String path = "/home/wangyi/下载/lucene-main/file/influxdb-2021-03-30T11-06-23.613.log";
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file),"Utf-16");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            return null;
        }
    }
    public static void transferFile(String srcFileName, String destFileName) throws IOException {
        String line_separator = "";
        FileInputStream fis = new FileInputStream(srcFileName);
        StringBuffer content = new StringBuffer();
        DataInputStream in = new DataInputStream(fis);
        BufferedReader d = new BufferedReader(new InputStreamReader(in, "UTF-16"));// , "UTF-8"
        String line = null;
        while ((line = d.readLine()) != null) {
            content.append(line + line_separator);
        }
        d.close();
        in.close();
        fis.close();

        Writer ow = new OutputStreamWriter(new FileOutputStream(destFileName), "UTF-8");
        ow.write(content.toString());
        ow.close();
    }


    public static void main(String[] args) throws JSONException, IOException {
        String srcFileName = "/media/charles/Data/WorkSpace/Idea/CodingStudy/ESTest/src/main/resources/noschema-log.txt";
        String destFileName = "/media/charles/Data/WorkSpace/Idea/CodingStudy/ESTest/src/main/resources/schema.txt";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/home/wangyi/下载/lucene-main/file/influxdb-2021-03-30T11-06-23.613-UTF-8.log"),"UTF-8"));

        String res = getJson();
        String[] ans= res.split("\n");
        List keyList = new ArrayList();
        for (int i = 0; i < ans.length; i++) {
            JSONObject json = new JSONObject(ans[i]);
            Iterator keys = json.keys();
            while (keys.hasNext()){
                String key = String.valueOf(keys.next());
                if(!keyList.contains(key)){
                    keyList.add(key);
                }
            }
        }

        for (int i = 0; i < ans.length; i++) {
            JSONObject json = new JSONObject(ans[i]);
            Iterator keys = json.keys();
            String str = "#";
            for (int j = 0; j < keyList.size(); j++) {
                if(json.has(keyList.get(j).toString())){
                    str = str + json.get(keyList.get(j).toString()).toString() + "#";
                }
                else{
                    str = str + "#";
                }
            }
            out.write(str + '\n');
            System.out.println(str);
        }
        out.close();

    }
}
