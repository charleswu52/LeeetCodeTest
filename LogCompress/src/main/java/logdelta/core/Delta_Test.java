package logdelta.core;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import logdelta.bean.Node;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

/**
 * @author WuChao
 * @create 2021/12/17 下午6:10
 */
public class Delta_Test {
    // 将c++ 版本的压缩代码改成 Java

    private static Stack<Node> deltaStack = new Stack<>();

    /*
    计算编辑聚类
     */
    public static void backtrackingPath(String basic, String str) {
        int m = basic.length();
        int n = str.length();

        int[][] dp = new int[m + 1][n + 1];
        // 初始化 dp 数组
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < n + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (basic.charAt(i - 1) == str.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        while (n >= 0 || m >= 0) {
            if (n != 0 && dp[m][n - 1] + 1 == dp[m][n]) {
                Node nodeA = new Node(m - 1, m, String.valueOf(str.charAt(n - 1)), 0);
                if (deltaStack.empty()) {
                    deltaStack.push(nodeA);
                } else {
                    Node nodeB = deltaStack.peek();
                    if (nodeB.getmType() == nodeA.getmType() && nodeB.getmLow() == nodeA.getmLow()) {
                        nodeA.setmContent(nodeA.getmContent() + nodeB.getmContent());
                        deltaStack.pop();
                    }
                    deltaStack.push(nodeA);
                }
//            cout << "insert " << s[n - 1] << " at " << m - 1 << endl;
                n = n - 1;
                continue;
            } else if (m != 0 && dp[m - 1][n] + 1 == dp[m][n]) {
//            cout << "delete " << basic[m - 1] << " at " << m - 1 << endl;
                Node nodeA = new Node(m - 1, m - 1, "", 1);
                if (deltaStack.empty()) {
                    deltaStack.push(nodeA);
                } else {
                    Node nodeB = deltaStack.peek();
                    if (nodeB.getmType() == nodeA.getmType() && nodeB.getmLow() == nodeA.getmLow() + 1) {
                        nodeA.setmHigh(nodeB.getmHigh());
                        deltaStack.pop();
                    }
                    deltaStack.push(nodeA);
                }
                m = m - 1;
                continue;
            } else if (m > 0 && n > 0 && dp[m - 1][n - 1] + 1 == dp[m][n]) {
//            cout << "replace " << basic[m - 2] << " to " << s[n - 1] << " at " << m - 1 << endl;
                Node nodeA = new Node(m - 1, m - 1, String.valueOf(str.charAt(n - 1)), 2);

                if (deltaStack.empty()) {
                    deltaStack.push(nodeA);
                } else {
                    Node nodeB = deltaStack.peek();
                    if (nodeB.getmType() == nodeA.getmType() && nodeB.getmLow() == nodeA.getmLow() + 1) {
                        nodeA.setmHigh(nodeB.getmHigh());
                        nodeA.setmContent(nodeA.getmContent() + nodeB.getmContent());
                        deltaStack.pop();
                    }
                    deltaStack.push(nodeA);
                }
                n = n - 1;
                m = m - 1;
                continue;
            }
            n = n - 1;
            m = m - 1;
        }
    }

    public static void processDelta(String logFilePath, String basicFilePath, String outputFilePath) throws Exception {
        File logFile = new File(logFilePath);
        File basicFile = new File(basicFilePath);
        if (!logFile.exists() || !basicFile.exists()) {
            System.out.println("文件不存在！");
            return;
        }
        File outputFile = new File(outputFilePath);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        BufferedReader basicReader = new BufferedReader(new InputStreamReader(new FileInputStream(basicFile), StandardCharsets.UTF_8));
        String basicStr = basicReader.readLine();
        basicReader.close();
        if (basicStr == null) {
            System.out.println("Basic String is NULL!");
            return;
        }
        System.out.println("basic string is: " + basicStr);

        BufferedReader logReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(logFile), StandardCharsets.UTF_8));
        int lineCount = 0;
        String log = null;
        while ((log = logReader.readLine()) != null) {
            lineCount++;
        }
        log = null;
        logReader.close();
        logReader = null;
        /*
        // 写入压缩文件
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(outputFile,true)));
        // 先写入文件大小
        out.writeInt(lineCount);
         */

        // 直接全部采用 kryo 进行序列化存储
        Output output = new Output(new FileOutputStream(outputFile, true));
        Kryo kryo = new Kryo();
        kryo.register(Node.class);

        // 先写入日志行数
        kryo.writeObject(output, lineCount);

        // 读取log 里的每一条数据 计算 delta  并序列化存储

        // 定义序列化器 并对要序列化的类进行注册
        logReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(logFile), StandardCharsets.UTF_8));
        while ((log = logReader.readLine()) != null) {
            backtrackingPath(basicStr, log);
            int deltaStackSize = deltaStack.size();
            // 将当前栈的大小进行写入
//            out.writeInt(deltaStackSize);
            kryo.writeObject(output, deltaStackSize);
            while (!deltaStack.empty()) {
                Node node = deltaStack.pop();
                // TODO 这里需要想一套对delta节点 Node 的序列化方法，查找了下 有原生方法，还有jackson protoBuff 等等
                // 这里使用 kryo 进行序列化存储
                kryo.writeObject(output, node);
            }
        }
        output.close();
        logReader.close();
    }
}
