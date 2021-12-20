package logdelta.util;

import javafx.util.Pair;
import logdelta.bean.BitSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/12/19 下午2:18
 */
public class Tools {
    // 设置一些全局变量
    public static List<Integer> C = new ArrayList<>(256);
    public static List<Integer> mapit = new ArrayList<>();
    public static List<Integer> remap = new ArrayList<>(256);

    static {
        Collections.fill(remap, -1);
    }

    public static List<List<Integer>> bucket = new ArrayList<>();

    public static String compress;
    public static List<Integer> sa;
    public static final int saFreq = 1;
    public static final int MAX = 999999;

    public static final int BUCKETSIZE = 256;

    public static final int INS = 0;
    public static final int DEL = 1;
    public static final int SUB = 2;


    // 提供一些 工具方法

    // 读取文件
    public static String readText(String baseFile, int length) throws Exception {
        System.out.println("reading base string...");
        File file = new File(baseFile);
        if (!file.exists()) {
            System.out.println("reading base string fail! can't find the file:" + baseFile);
            throw new Exception("文件不存在！");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8));
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            if (length > 0 && stringBuffer.length() >= length) {
                break;
            }
            if (temp.charAt(0) != '>') {
                stringBuffer.append(temp);
            }
        }
        if (length > 0 && length < stringBuffer.length()) {
            stringBuffer.setLength(length);
        }
        reader.close();
        System.out.println("read file ok!");
        return stringBuffer.toString();
    }

    /**
     * 比较两个字符串的大小转换成数字比较 是特殊格式的比较
     * String a = "_123456.";
     * String b = "_12345.";
     * cmp(a, b);
     *
     * @param a
     * @param b
     * @return
     */
    public static int cmp(String a, String b) throws Exception {
        int ap = a.indexOf(".");
        int ah = a.lastIndexOf("_") + 1;
        int bp = b.indexOf(".");
        int bh = b.lastIndexOf("_") + 1;
        if (ap == -1 || bp == -1) {
            throw new Exception("字符串匹配格式不正确");
        }

        StringBuffer as = new StringBuffer(), bs = new StringBuffer();
        for (int i = ah; i < ap; i++) {
            as.append(a.charAt(i));
        }
        for (int i = bh; i < bp; i++) {
            bs.append(b.charAt(i));
        }
        int aa = Integer.parseInt(as.toString());
        int bb = Integer.parseInt(bs.toString());
        return aa - bb;
    }

    /**
     * 将int数字转换成char数组
     * 直接使用 String.valueOf 代替
     */
    public static String itoa(int num) {
        StringBuffer stringBuffer = new StringBuffer();
        int sign = num, i = 0, j = 0;
        char[] temp = new char[11];
        if (sign < 0) {
            num = -num;
        }
        do {
            temp[i] = (char) (num % 10 + '0');
            num /= 10;
            i++;
        } while (num > 0);
        if (sign < 0) {
            temp[i++] = '-';
        }
        temp[i] = '\0';
        i--;
        while (i >= 0) {
            stringBuffer.append(temp[i]);
            i--;
        }
        return stringBuffer.toString();
    }

    public static void getFileInDir(String dir, List<String> files) {
        File dirs = new File(dir);
        File[] listFiles = dirs.listFiles();
        for (File f : listFiles) {
            files.add(f.getAbsolutePath());
        }
        Collections.sort(files, (o1, o2) -> {
            try {
                return cmp(o1, o2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

    public static void str2gramsHashTable(String s, List<Integer> res, int q) {
        for (int i = 0; i < s.length() - q + 1; i++) {
            res.add(s.substring(i, q).hashCode());
        }
    }

    public static void str2gramsPos(String s, List<Integer> res, int q) {
        for (int i = 0; i < s.length() - q + 1; i++) {
            res.add(s.substring(i, q).hashCode());
        }
    }

    public static int minimize(List<List<Pair<Integer, BitSet>>> arrayLists) {
        // TODO 需要自定义BitSet
        int deltaNum = BitSet.getNum() / 2;
        int num = 0, pos = 0, min = MAX, listSize = arrayLists.size(), forward = 0, arrSize = 0;
        List<Pair<Integer, BitSet>> array = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            array = arrayLists.get(i);
            if (array != null) {
                num += forward;
                if (num < min) {
                    min = num;
                    pos = i;
                }
                forward = 0;
                //cout<<"num:"<<num<<"forward:"<<forward<<endl;
                continue;
            }
            arrSize = array.size();
            //cout<<arrSize-2<<endl;
            num += (arrSize - 2) * deltaNum - array.get(arrSize - 2).getKey() + forward;// (arrSize - 2) is head, and (arrSize - 1) is tail
            forward = array.get(arrSize - 1).getKey() - (arrSize - 2) * deltaNum;
            //cout<<"num:"<<num<<"forward:"<<forward<<endl;
            if (num < min) {
                min = num;
                pos = i;
            }
        }
        return pos;
    }

    public static int minimizeSpan(List<List<Pair<Integer, BitSet>>> arrayLists) {
        // TODO 需要自定义 BitSet
        int deltaNum = BitSet.getNum() / 2;
        int num = 0, pos = 0, min = MAX, listSize = arrayLists.size(), forward = 0, arrSize = 0;
        List<Pair<Integer, BitSet>> array;
        for (int i = 0; i < listSize; i++) {
            array = arrayLists.get(i);
            if (array != null) {
                num += forward;
                if (num < min) {
                    min = num;
                    pos = i;
                }
                forward = 0;
                //cout<<"num:"<<num<<"forward:"<<forward<<endl;
                continue;
            }
            arrSize = array.size();
            //cout<<arrSize-2<<endl;
            num += array.get(arrSize - 1).getKey() + forward;
            forward = -array.get(arrSize - 2).getKey();
            //cout<<"num:"<<num<<"forward:"<<forward<<endl;
            if (num < min) {
                min = num;
                pos = i;
            }
        }
        return pos;
    }

    public static void querySegment(String query, List<String> querySegs, int k) {
        int queryLength = query.length();
        int querySegLength = queryLength / k;
        int remainder = queryLength % k;
        int i;
        int pos = 0;
        for (i = 0; i < remainder; i++) {
            String str = query.substring(pos, querySegLength + 1);
            querySegs.add(str);
            pos += querySegLength + 1;
        }
        for (i = remainder; i < k; i++) {
            String str = query.substring(pos, querySegLength);
            querySegs.add(str);
            pos += querySegLength;
        }
    }

    public static int occ(char ch, int k) {
        assert (k >= 0);
        int b = k / BUCKETSIZE;
        int l = b * BUCKETSIZE;
        int count = bucket.get(b).get(remap.get(ch));

        for (int i = l; i < k; i++) {
            if (compress.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public static int backward(int k) {
        return C.get(remap.get(compress.charAt(k))) + occ(compress.charAt(k), k);
    }

    public static int locate(int pos, int length) {
        int count = 0;
        // TODO saFreq 设置的有问题 这个循环不会进入
        while ((pos % saFreq) != 0) {
            pos = backward(pos);
            count++;
        }
        return (count + sa.get(pos / saFreq)) % length;
    }

    public static int backwardSearch(String pat, int length, List<Integer> positions) {
        int left = 0, high = length;
        for (int i = pat.length() - 1; i >= 0; i--) {
            int k = remap.get(pat.charAt(i));
            left = C.get(k) + occ(pat.charAt(i), left);
            high = C.get(k) + occ(pat.charAt(i), high);
        }
        for (int i = left; i < high; i++) {
            positions.add(locate(i, length));
        }
        return high - left;
    }

}
