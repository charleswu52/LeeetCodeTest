package logdelta.core;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import javafx.geometry.VPos;
import javafx.util.Pair;
import logdelta.bean.BitSet;
import logdelta.bean.MultiNode;
import logdelta.bean.Node;
import logdelta.util.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/**
 * 日志压缩的类
 *
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
    private long mQueryCost;
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
        System.out.println("gram length: " + this.mGramLength);
    }

    public void loadBaseString(String baseFile, int baseSize) throws Exception {

        this.mBaseString = Tools.readText(baseFile, baseSize);

        System.out.println("source string length: " + this.mBaseString.length());
        System.out.println("========= read base file is over =========");

    }

    /*
    读取 压缩后的delta文件 *.dlt
     */
    public void loadDelta(List<String> deltaFiles) throws Exception {
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

    public void saveDeltaIndex(String deltaIndexFile) throws Exception {
        String deltaIndexExt = deltaIndexFile + ".dlt";
        File file = new File(deltaIndexExt);
        if (!file.exists()) {
            file.createNewFile();
        }
        Output output = new Output(new FileOutputStream(file, true));
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

    public void loadDeltaIndex(String deltaIndexFile) throws Exception {
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

    public void saveInvertedLists(String invertedListFile) throws Exception {
        String invertedListFileExt = invertedListFile + ".inv";
        File file = new File(invertedListFileExt);
        if (!file.exists()) {
            file.createNewFile();
        }
        Output output = new Output(new FileOutputStream(file, true));
        Kryo kryo = new Kryo();
        // TODO 待思考
        kryo.register(BitSet.class);
        kryo.register(char[].class);

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

        int invertedListSize = gramListMap.size();
        kryo.writeObject(output, invertedListSize);

        // 写倒排 TODO 待思考 序列化方式
        Iterator<Map.Entry<Integer, List<Pair<Integer, BitSet>>>> iterator = gramListMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Pair<Integer, BitSet>>> next = iterator.next();
            Integer key = next.getKey();
            kryo.writeObject(output, key);
            List<Pair<Integer, BitSet>> value = next.getValue();
            int size = value.size();
            kryo.writeObject(output, size);
            for (int i = 0; i < size; i++) {
                Integer key1 = value.get(i).getKey();
                BitSet value1 = value.get(i).getValue();
                kryo.writeObject(output, key1);
                kryo.writeObject(output, value1);
            }
        }
        output.close();
        System.out.println("========= save Inverted list is over =========");
    }

    // 加载序列化的倒排索引 并 反序列化
    public void loadInvertedLists(String invertedListFile) throws Exception {
        String invertedListFileExt = invertedListFile + ".inv";
        File file = new File(invertedListFileExt);
        if (!file.exists()) {
            System.out.println("文件不存在" + invertedListFileExt);
            throw new Exception("文件不存在异常退出");
        }
        Input input = new Input(new FileInputStream(file));
        Kryo kryo = new Kryo();
        kryo.register(BitSet.class);
        kryo.register(char[].class);

        int hashKey = 0, position = 0;
        int mbfSize = kryo.readObject(input, Integer.class);
        this.mBaseFile = kryo.readObject(input, String.class);
        loadBaseString(this.mBaseFile, mbfSize);

        this.mGramLength = kryo.readObject(input, Integer.class);

        int bitSetSize = kryo.readObject(input, Integer.class);
        BitSet.setNum(bitSetSize);

        int bufSize = (bitSetSize >> 3) + ((bitSetSize & 0x7) != 0 ? 1 : 0);
        char[] buf = new char[bufSize];

        this.mAnchorSpan = kryo.readObject(input, Character.class);
        this.mDeltaNum = kryo.readObject(input, Integer.class);

        int invertedListSize = kryo.readObject(input, Integer.class);
        for (int i = 0; i < invertedListSize; i++) {
            int key = kryo.readObject(input, Integer.class);
            List<Pair<Integer, BitSet>> value = new ArrayList<>();
            int size = kryo.readObject(input, Integer.class);
            for (int j = 0; j < size; j++) {
                Integer key1 = kryo.readObject(input, Integer.class);
                BitSet value1 = kryo.readObject(input, BitSet.class);
                value.add(new Pair<>(key1, value1));
            }
            this.mGramInvertedList.getGramListMap().put(key, value);
        }
        System.out.println("========= loading inverted index is over =========");
    }

    public void buildReservedSequence() {
        for (int i = 0; i < mDeltas.size(); i++) {
            for (int j = 0; j < mDeltas.get(i).size(); j++) {
                int type = mDeltas.get(i).get(j).getmType();
                if (type != 0) {
                    int low = mDeltas.get(i).get(j).getmLow();
                    int high = mDeltas.get(i).get(j).getmHigh();
                    char[] chars = this.mBaseString.toCharArray();
                    for (int k = low; k <= high; k++) {
                        chars[k] = 'a';
                    }
                    this.mBaseString = new String(chars);
                }
            }
        }
        System.out.println("========= build reserved sequence is over =========");
    }

    public void search(int searchType, List<String> queryStrings, int threshold) {
        this.mThreshold = threshold;
        this.mVerifyNum = 0;
        switch (searchType) {
            case 1:
                basicVerify(queryStrings);
                break;
            case 3:
                minVerify(queryStrings);
                break;
            case 7:
                minVerifyED(queryStrings);
                break;
            default:
                System.out.println("No such search type!");
                break;
        }
    }

    public void basicVerify(List<String> queryStrings) {
        if (mBaseString.isEmpty()) {
            return;
        }
        int deltaNum = mDeltas.size();
        int queryNum = queryStrings.size();
        this.mResultNum = 0;
        this.mResultDeltaNum = 0;

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < queryNum; i++) {
            System.out.println("-----results of query " + i + "-----");
            List<Set<Integer>> results = new ArrayList<>();
            basicVerify(queryStrings.get(i), results);

            searchIncr(queryStrings.get(i), results);
            this.mResultDeltaNum = outputResult(results, this.mResultDeltaNum);

            if (i == queryNum - 1) {
                System.out.println("total searching reserved substring verify number: " + this.mVerifyNum);
            }
            this.mResultNum += getNum(results);
        }
        this.mQueryCost = System.currentTimeMillis() - startTime;
        outputCost();


    }

    public void basicVerify(String queryString, List<Set<Integer>> results) {
        int deltaNum = this.mDeltas.size();
        List<Pair<Integer, BitSet>> array;
        List<Integer> gramLists = new ArrayList<>();
        Tools.str2gramsPos(queryString, gramLists, this.mGramLength);
        int listSize = gramLists.size();
        List<List<Pair<Integer, BitSet>>> arrayLists = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            array = this.mGramInvertedList.getArray(gramLists.get(i));
            arrayLists.add(array);
        }
        for (int i = 0; i < listSize; i++) {
            array = arrayLists.get(i);
            if (array == null) {
                continue;
            }
            int arraySize = array.size() - 2;
            for (int j = 0; j < arraySize; j++) {
                Pair<Integer, BitSet> gramPair = array.get(j);
                int posInB = gramPair.getKey();
                for (int deltaId = 0; deltaId < deltaNum; deltaId++) {
                    mVerifyNum++;

                }
            }
        }

    }

    public void searchIncr(String queryString, List<Set<Integer>> results) {

    }


    public void minVerifyED(List<String> queryStrings) {

    }

    public void minVerify(List<String> queryStrings) {

    }

    public void verifyOnSingleDelta(int deltaID, int posInB, int posInP, String pattern, List<Set<Integer>> results) {
        List<Node> array = this.mDeltas.get(deltaID);
        int startPos = 0, endPos = 0;
        int deltaPos = binarySearch(array, posInB);
        if (deltaPos >= 0 && bDestoriedGarm(array.get(deltaPos), posInB)) {
            return;
        }

    }

    public int outputResult(List<Set<Integer>> results, int mResultDeltaNum) {
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).isEmpty()) {
                continue;
            }
            System.out.println("delta " + i + ": ");
            mResultDeltaNum++;
            System.out.println(results.get(i));
        }
        return this.mResultDeltaNum;
    }

    public int getNum(List<Set<Integer>> results) {
        int total = 0;
        for (int i = 0; i < results.size(); i++) {
            total += results.get(i).size();
        }
        return total;
    }

    public void outputCost() {
        System.out.println("total result delta number: " + this.mResultDeltaNum + "\ntotal result number: " + this.mResultNum +
                "\navg query search time: " + this.mQueryCost + " ms\n");
    }


    //
    public void showDelta() {
        System.out.println("log size:" + mDeltas.size());
        for (int i = 0; i < mDeltas.size(); i++) {
            System.out.println("sequence " + i);
            ;
            for (int j = 0; j < mDeltas.get(i).size(); j++) {
                System.out.println("delta " + j);
                System.out.println(mDeltas.get(i).get(j));
            }
        }
    }

    //------------------------------下面是私有方法-------------------------------------


    private int binarySearch(List<Node> array, int t) {
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

    private boolean bDestoriedGarm(Node node, int posInB) {
        return node.getmLow() < (posInB + mGramLength);
    }

    private boolean bDestoriedCandidate(MultiNode node, int posInB, Set<Integer> candidate) {
        if (node.getmLow() < (posInB + mGramLength)) {
            candidate.remove(node.getmDelta());
            return true;
        }
        return false;
    }

    private int verifyRightOnSingleDelta(List<Node> array, int deltaPos, int posInB, int posInP, String pattern) {
        int remain = pattern.length() - posInP;
        int length;
        while (remain != 0) {
            if (deltaPos < 0 || deltaPos >= array.size()) {
                if (mBaseString.length() - posInB < remain
                        || mBaseString.substring(posInB, posInB + remain).compareTo(pattern.substring(posInP, posInP + remain)) != 0) {
                    return -1;
                }
                return posInB + remain - 1;
            } else if (array.get(deltaPos).getmLow() > posInB + remain - 1) {
                if (mBaseString.substring(posInB, posInB + remain).compareTo(pattern.substring(posInP, posInP + remain)) !=0)
                    return -1;
                return posInB + remain - 1;
            } else {
                length = array.get(deltaPos).getmLow() - posInB;
                if (mBaseString.substring(posInB, posInB + length).compareTo(pattern.substring(posInP, posInP + length)) != 0)
                    return -1;
                posInB += length;
                posInP += length;
                remain -= length;
                length = array.get(deltaPos).getmContent().length();
                switch (array.get(deltaPos).getmType()) {
                    case Tools.INS:
                        length = Math.min(length, remain);
                        if (pattern.substring(posInP,posInP+length).compareTo(
                                array.get(deltaPos).getmContent().substring(0,remain)) != 0)
                            return -1;
                        posInP += length;
                        remain -= length;
                        break;
                    case Tools.DEL:
                        posInB = array.get(deltaPos).getmHigh() + 1;
                        break;
                    case Tools.SUB:
                        length = Math.min(length, remain);
                        if (pattern.substring(posInP,posInP+length).compareTo(
                                array.get(deltaPos).getmContent().substring(0,remain)) != 0)
                            return -1;
                        posInP += length;
                        posInB += length;
                        remain -= length;
                        break;
                }
                deltaPos++;
            }

        }
        return posInB + remain - 1;
    }


}
