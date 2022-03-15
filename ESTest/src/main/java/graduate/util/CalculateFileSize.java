package graduate.util;

import java.io.File;

/**
 * @author WuChao
 * @create 2022/3/14 下午2:24
 */
public class CalculateFileSize {
    // 计算文件大小

    public static void main(String[] args) {
        String filePath = "/media/charles/Data/研究生学习/华为压缩索引检索项目/ES实验/logs_trans/readJson";
        long size = calculate(filePath);
        print(size);

    }

    public static long calculate(String path) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        long sum = 0;
        for (int i = 0; i < tempList.length; i++) {
            sum += getFileSize(tempList[i]);
        }
        return sum;
    }

    public static long getFileSize(File file) {
        return file.length();
    }

    public static void print(long size) {
        double kb = size / 1024.0;
        double mb = size / 1024.0 / 1024.0;
        double gb = size / 1024.0 / 1024.0 / 1024.0;
        System.out.println("当前文件/夹 大小为：" + size + " 字节,= " + kb + " KB,= " + mb + " MB,=" + gb + " GB.");
    }
}
