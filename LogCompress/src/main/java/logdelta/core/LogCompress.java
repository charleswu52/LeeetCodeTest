package logdelta.core;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import javafx.util.Pair;
import logdelta.bean.BitSet;
import logdelta.bean.Node;
import logdelta.util.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 日志压缩的类
 * @author WuChao
 * @create 2021/12/19 下午1:07
 */
public class LogCompress {
    public static final int BASELENGTH = 1000000;

    // 私有变量区域

    private String mBaseFile;
    private String mBaseString;
    private int mGramLength;

    private int mResultNum;
    private int mResultDeltaNum;
    private double mQueryCost;
    private int mThreshold;
    private long mVerifyNum;
    private char mAnchorSpan;
    private int mDeltaNum;
    private List<List<Node>> mDeltas;
    private GramInvertedLists mGramInvertedList;
    private Stack<Node> deltaC;
    private int deltaFlag;
    private int changeFlag;


    // public 方法

    public LogCompress() {
    }

    public void setmGramLength(int mGramLength) {
        this.mGramLength = mGramLength;
    }

    public void setmAnchorSpan(char mAnchorSpan) {
        this.mAnchorSpan = mAnchorSpan;
    }

    public void setmBaseFile(String mBaseFile) {
        this.mBaseFile = mBaseFile;
    }

    public int getBaseSize() {
        return this.mBaseString.length();
    }

    public void getConfiguration() {
        System.out.println("baseString length: " + this.mBaseString.length());
        System.out.println("gram length: "+this.mGramLength);
    }

    public void loadBaseString(String baseFile, int baseSize)throws Exception {

        this.mBaseString = Tools.readText(baseFile,baseSize);

        System.out.println("source string length: " + this.mBaseString.length());
        System.out.println("========= read base file is over =========");

    }

    /*
    读取 压缩后的delta文件 *.dlt
     */
    public void loadDelta(List<String> deltaFiles)throws Exception {
        this.mDeltaNum = deltaFiles.size();
        for (int i = 0; i < this.mDeltaNum; i++) {
            // TODO 这个地方跟 c++ 版本是有差异的 注意区分
//            List<Node> delta = new ArrayList<>();
            File file = new File(deltaFiles.get(i));
            if (!file.exists()) {
                System.out.println("该文件不存在：" + deltaFiles.get(i));
                throw new Exception("文件不存在");
            }
            // 按照序列化的方式进行读取

            Input input = new Input(new FileInputStream(file));
            Kryo kryo = new Kryo();
            kryo.register(Node.class);

            // 当前日志的总条数
            int logLength = kryo.readObject(input, Integer.class);
            for (int j = 0; j < logLength; j++) {
                // 读取一条日志是由几个 delta node 组成的
                int deltaNodeSize = kryo.readObject(input, Integer.class);
                List<Node> list = new ArrayList<>();
                for (int k = 0; k < deltaNodeSize; k++) {
                    Node node = kryo.readObject(input, Node.class);
                    list.add(node);
                }
                mDeltas.add(list);
            }
            input.close();
        }
//        showDelta();
        System.out.println("reading delta files count: " + this.mDeltaNum);
        System.out.println("reading delta count: " + this.mDeltas.size());
        System.out.println("========= build delta index is over =========");
    }

    public void saveDeltaIndex(String deltaIndexFile)throws Exception {
        String deltaIndexExt = deltaIndexFile + ".dlt";
        File file = new File(deltaIndexExt);
        if (!file.exists()) {
            file.createNewFile();
        }
        Output output = new Output(new FileOutputStream(file,true));
        Kryo kryo = new Kryo();
        kryo.register(Node.class);

        int logSize = mDeltas.size();
        kryo.writeObject(output, logSize);
        for (int i = 0; i < logSize; i++) {
            int logDeltaSize = this.mDeltas.get(i).size();
            kryo.writeObject(output, logDeltaSize);
            for (int j = 0; j < logDeltaSize; j++) {
                Node node = this.mDeltas.get(i).get(j);
                kryo.writeObject(output, node);
            }
        }
        output.close();
        System.out.println("========= save delta index is over =========");
    }

    public void loadDeltaIndex(String deltaIndexFile)throws Exception {
        String deltaIndexExt = deltaIndexFile + ".dlt";
        File file = new File(deltaIndexExt);
        if (!file.exists()) {
            System.out.println("文件不存在");
            throw new Exception("文件不存在");
        }
        Input input = new Input(new FileInputStream(file));
        Kryo kryo = new Kryo();
        kryo.register(Node.class);
        Integer logSize = kryo.readObject(input, Integer.class);
        this.mDeltaNum = logSize;
        for (int i = 0; i < logSize; i++) {
            List<Node> deltaNodes = new ArrayList<>();
            int deltas = kryo.readObject(input, Integer.class);
            for (int j = 0; j < deltas; j++) {
                Node node = kryo.readObject(input, Node.class);
                deltaNodes.add(node);
            }
            this.mDeltas.add(deltaNodes);
        }
        input.close();
        System.out.println("========= load delta index is over =========");
    }

    public void buildInvertedLists() {
        this.mGramInvertedList.buildGramInvertedLists(this.mBaseString, mGramLength, mDeltas);
        //m_gramInvertedList.showInvertedListSize();
        System.out.println("========= build Inverted Lists is over =========");
        //m_gramInvertedList.show();
    }

    public void saveInvertedLists(String invertedListFile)throws Exception {
        String invertedListFileExt = invertedListFile + ".inv";
        File file = new File(invertedListFileExt);
        if (!file.exists()) {
            file.createNewFile();
        }
        Output output = new Output(new FileOutputStream(file, true));
        Kryo kryo = new Kryo();
        // TODO 待思考
        kryo.register(GramInvertedLists.class);

        Map<Integer, List<Pair<Integer, BitSet>>> gramListMap = this.mGramInvertedList.getGramListMap();
        int mbfSize = mBaseFile.length();
        // 写文件名 和 文件名长度
        kryo.writeObject(output, mbfSize);
        kryo.writeObject(output, mBaseFile);

        int mbsSize = mBaseString.length();
        kryo.writeObject(output, mbsSize);

        kryo.writeObject(output, mGramLength);

        int bitSetSize = BitSet.getNum();
        kryo.writeObject(output, bitSetSize);
        kryo.writeObject(output, mAnchorSpan);
        kryo.writeObject(output, mDeltaNum);

        // 写倒排 TODO 待思考 序列化方式
        kryo.writeObject(output, gramListMap);

        output.close();
        System.out.println("========= save Inverted list is over =========");



    }









    //
    public void showDelta() {
        System.out.println("log size:" + mDeltas.size());
        for(int i = 0; i< mDeltas.size(); i++)
        {
            System.out.println("sequence "+i);;
            for(int j =0; j< mDeltas.get(i).size(); j++)
            {
                System.out.println("delta "+j);
                System.out.println(mDeltas.get(i).get(j));
            }
        }
    }

    //------------------------------下面是私有方法-------------------------------------


    private int binarySearch(List<Node> array, int t){
        int left = -1;
        int n = array.size();
        int right = n;
        while (left + 1 != right) {
            int mid = left + (right - left) / 2;
            if (array.get(mid).getmHigh() < t) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (right >= n) {
            return -1;
        }
        return right;
    }






}
