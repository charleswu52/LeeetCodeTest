package logdelta.core;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import javafx.util.Pair;
import logdelta.bean.BitSet;
import logdelta.bean.MultiNode;
import logdelta.bean.Node;
import logdelta.util.Tools;

import java.io.*;
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
    private int deleteFlag;
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

    public void search(int searchType, List<String> queryStrings, int threshold) throws Exception {
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

    private void basicVerify(List<String> queryStrings) {
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

    private void basicVerify(String queryString, List<Set<Integer>> results) {
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
                    verifyOnSingleDelta(deltaId, posInB, i, queryString, results);
                }
            }
        }
    }

    // 搜索增量子串 算法
    private void searchIncr(String queryString, List<Set<Integer>> results) {
        List<List<Node>> Rn = new ArrayList<>();
        List<List<Integer>> rsn = new ArrayList<>();

        //int deltaNum = m_deltas.size();
        for (int i = 0; i < mDeltas.size(); i++) {
            List<Node> R = new ArrayList<>();
            List<Integer> rStart = new ArrayList<>();
            //int deltaNum = m_deltas[i].size();
            for (int j = 0; j < mDeltas.get(i).size(); j++) {
                if (R.size() == 0) {
                    if (mDeltas.get(i).get(j).getmType() == 0) {
                        Node r = new Node(
                                mDeltas.get(i).get(j).getmHigh(),
                                mDeltas.get(i).get(j).getmLow(),
                                mDeltas.get(i).get(j).getmContent(),
                                mDeltas.get(i).get(j).getmType()
                        );
                        if (r.getmLow() + 1 < mGramLength) {
                            r.setmContent(mBaseString.substring(0, r.getmLow() + 1) + mDeltas.get(i).get(j).getmContent());
                            r.setmLow(-1);
                        }
                        r.setmType(3);
                        R.add(r);
                        rStart.add(0);
                    } else if (mDeltas.get(i).get(j).getmType() == 2) {
                        Node r = new Node(
                                mDeltas.get(i).get(j).getmLow(),
                                mDeltas.get(i).get(j).getmHigh(),
                                mDeltas.get(i).get(j).getmContent(),
                                mDeltas.get(i).get(j).getmType()
                        );
                        if (r.getmLow() < mGramLength) {
                            r.setmContent(mBaseString.substring(0, r.getmLow()) + mDeltas.get(i).get(j).getmContent());
                            r.setmLow(0);
                        }
                        r.setmType(4);
                        R.add(r);
                        rStart.add(1);
                    } else if (mDeltas.get(i).get(j).getmType() == 1) {
                        if (mDeltas.get(i).get(j).getmLow() < mGramLength) {
                            Node r = new Node(
                                    0,
                                    mDeltas.get(i).get(j).getmHigh(),
                                    mBaseString.substring(0, mDeltas.get(i).get(j).getmLow()),
                                    4
                            );
                            R.add(r);
                            rStart.add(1);
                        }
                    }
                } else {
                    int rTop = R.size() - 1;
                    if (mDeltas.get(i).get(j).getmType() == 0) {
                        Node r = new Node(
                                mDeltas.get(i).get(j).getmHigh(),
                                mDeltas.get(i).get(j).getmLow(),
                                mDeltas.get(i).get(j).getmContent(),
                                mDeltas.get(i).get(j).getmType()
                        );
                        if (R.get(rTop).getmType() == 3) {
                            int flag = 0;
                            int a = 0;
                            if (r.getmLow() - R.get(rTop).getmHigh() + 1 < mGramLength) {
                                if (r.getmLow() - R.get(rTop).getmHigh() + 1 == 0) {
                                    r.setmContent(R.get(rTop).getmContent() + mDeltas.get(i).get(j).getmContent());
                                } else {
                                    r.setmContent(R.get(rTop).getmContent() +
                                            mBaseString.substring(R.get(rTop).getmHigh(), r.getmLow() - R.get(rTop).getmHigh() + 1)
                                            + mDeltas.get(i).get(j).getmContent());
                                }
                                r.setmLow(R.get(rTop).getmLow());
                                flag = 1;
                                R.remove(R.size() - 1);
                                a = rStart.get(rStart.size() - 1);
                                rStart.remove(rStart.size() - 1);
                            }
                            r.setmType(3);
                            R.add(r);
                            if (flag == 0) {
                                rStart.add(0);
                            } else {
                                rStart.add(a);
                            }
                        } else if (R.get(rTop).getmType() == 4) {
                            int flag = 0;
                            int a = 0;
                            if (r.getmLow() - R.get(rTop).getmHigh() < mGramLength) {
                                if (r.getmLow() - R.get(rTop).getmHigh() == 0) {
                                    r.setmContent(R.get(rTop).getmContent() + mDeltas.get(i).get(j).getmContent());
                                } else {
                                    r.setmContent(R.get(rTop).getmContent() +
                                            mBaseString.substring(R.get(rTop).getmHigh() + 1, r.getmLow() - R.get(rTop).getmHigh())
                                            + mDeltas.get(i).get(j).getmContent());
                                }
                                r.setmLow(R.get(rTop).getmLow());
                                flag = 1;
                                R.remove(R.size() - 1);
                                a = rStart.get(rStart.size() - 1);
                                rStart.remove(rStart.size() - 1);
                            }
                            r.setmType(3);
                            R.add(r);
                            if (flag == 0) {
                                rStart.add(0);
                            } else {
                                rStart.add(a);
                            }
                        }
                    } else if (mDeltas.get(i).get(j).getmType() == 2) {
                        Node r = new Node(
                                mDeltas.get(i).get(j).getmLow(),
                                mDeltas.get(i).get(j).getmHigh(),
                                mDeltas.get(i).get(j).getmContent(),
                                mDeltas.get(i).get(j).getmType()
                        );
                        if (R.get(rTop).getmType() == 3) {
                            int flag = 0;
                            int a = 0;
                            if (r.getmLow() - R.get(rTop).getmHigh() < mGramLength) {
                                if (r.getmLow() - R.get(rTop).getmHigh() == 0) {
                                    r.setmContent(R.get(rTop).getmContent() + mDeltas.get(i).get(j).getmContent());
                                } else {
                                    r.setmContent(R.get(rTop).getmContent() +
                                            mBaseString.substring(R.get(rTop).getmHigh(), r.getmLow() - R.get(rTop).getmHigh())
                                            + mDeltas.get(i).get(j).getmContent());
                                }
                                r.setmLow(R.get(rTop).getmLow());
                                flag = 1;
                                R.remove(R.size() - 1);
                                a = rStart.get(rStart.size() - 1);
                                rStart.remove(rStart.size() - 1);
                            }
                            r.setmType(4);
                            R.add(r);
                            if (flag == 0) {
                                rStart.add(0);
                            } else {
                                rStart.add(a);
                            }

                        } else if (R.get(rTop).getmType() == 4) {
                            int flag = 0;
                            int a = 0;
                            if (r.getmLow() - R.get(rTop).getmHigh() - 1 < mGramLength) {
                                if (r.getmLow() - R.get(rTop).getmHigh() - 1 == 0) {
                                    r.setmContent(R.get(rTop).getmContent() + mDeltas.get(i).get(j).getmContent());
                                } else {
                                    r.setmContent(R.get(rTop).getmContent() +
                                            mBaseString.substring(R.get(rTop).getmHigh() + 1, r.getmLow() - R.get(rTop).getmHigh() - 1)
                                            + mDeltas.get(i).get(j).getmContent());
                                }
                                r.setmLow(R.get(rTop).getmLow());
                                flag = 1;
                                R.remove(R.size() - 1);
                                a = rStart.get(rStart.size() - 1);
                                rStart.remove(rStart.size() - 1);
                            }
                            r.setmType(4);
                            R.add(r);
                            if (flag == 0) {
                                rStart.add(0);
                            } else {
                                rStart.add(a);
                            }

                        }

                    } else if (mDeltas.get(i).get(j).getmType() == 1) {
                        if (R.get(rTop).getmType() == 3) {
                            int flag = 0;
                            if (mDeltas.get(i).get(j).getmLow() - R.get(rTop).getmHigh() < mGramLength) {
                                if (mDeltas.get(i).get(j).getmLow() - R.get(rTop).getmHigh() != 0) {
                                    R.get(rTop).setmContent(R.get(rTop).getmContent() +
                                            mBaseString.substring(R.get(rTop).getmHigh(), mDeltas.get(i).get(j).getmLow() - R.get(rTop).getmHigh()));
                                } else {
                                    // TODO 这块有毛病
                                    R.get(rTop).setmContent(R.get(rTop).getmContent());
//                                    R[rTop].m_content = R[rTop].m_content;
                                }
                                R.get(rTop).setmHigh(mDeltas.get(i).get(j).getmHigh());
                                flag = 1;
                                R.get(rTop).setmType(4);
                            }
                        } else if (R.get(rTop).getmType() == 4) {
                            int flag = 0;
                            if (mDeltas.get(i).get(j).getmLow() - R.get(rTop).getmHigh() - 1 < mGramLength) {
                                if (mDeltas.get(i).get(j).getmLow() - R.get(rTop).getmHigh() - 1 != 0) {
                                    R.get(rTop).setmContent(R.get(rTop).getmContent() +
                                            mBaseString.substring(R.get(rTop).getmHigh() + 1,
                                                    mDeltas.get(i).get(j).getmLow() - R.get(rTop).getmHigh() - 1));
                                } else {
                                    R.get(rTop).setmContent(R.get(rTop).getmContent());
                                }
                                R.get(rTop).setmHigh(mDeltas.get(i).get(j).getmHigh());
                                flag = 1;
                                R.get(rTop).setmHigh(4);
                            }
                        }
                    }
                }
            }
            Rn.add(R);
            rsn.add(rStart);
        }
    /*for(int i=0; i<Rn.size(); i++)
        for(int j=0; j<Rn[i].size(); j++)
        {
            cout<<Rn.get(i).get(j).m_content<<endl<<Rn.get(i).get(j).m_low<<endl<<Rn.get(i).get(j).getmHigh()<<endl<<Rn.get(i).get(j).getmType()<<endl;
        }
    for(int i=0; i<rsn.size(); i++)
        for(int j=0; j<rsn[i].size(); j++)
        {
            cout<<"rssssssn:"<<rsn[i][j]<<endl;
        }*/
    /*for(int i=0;i<R.size();i++)
    {
        cout<<R[i].m_content<<endl<<R[i].m_low<<endl<<R[i].getmHigh()<<endl<<R[i].getmType()<<endl;
    }*/

        int deltaNum = mDeltas.size();
        for (int i = 0; i < deltaNum; i++) {
            for (int j = 0; j < Rn.get(i).size(); j++) {
                if (Rn.get(i).get(j).getmContent().length() >= (queryString.length() - 2 * (mGramLength - 1))) {
                    String a = Rn.get(i).get(j).getmContent();
                    int pSub = a.indexOf(queryString.substring(mGramLength - 1,
                            queryString.length() - mGramLength - (mGramLength - 1) + 1));
                    //cout<<"aaaaaaaaaaaaaaaaaaaaaaaaaa------------------------"<<endl;
                    if (pSub != -1) {
                        int p = a.indexOf(queryString);
                        int pcLeft = a.indexOf(queryString.substring(mGramLength - 1,
                                queryString.length() - (mGramLength - 1)));
                        //cout<<"bbbbbbbbbbbbbbbb------------------------"<<endl;
                        int pcRight = a.indexOf(queryString.substring(0, queryString.length() - (mGramLength - 1)));
                        //cout<<"cccccccccccccccccccccccc-----------------------"<<endl;
                        if (p != -1) {
                            int pB = Rn.get(i).get(j).getmLow(), flag = 0;

                            for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                    flag = k;
                                    break;
                                }
                            }
                            int num = 0;
                            for (int k = 0; k < flag; k++) {
                                if (mDeltas.get(i).get(k).getmType() == 0) {
                                    num = num + mDeltas.get(i).get(k).getmContent().length();
                                } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                    num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                    //cout<<num;
                                }
                            }
                            if (rsn.get(i).get(j) == 0) {
                                results.get(i).add(pB + num + p + 1);
                            } else {
                                results.get(i).add(pB + num + p);
                            }

                        } else if (pcLeft != -1 && ((mGramLength - 1) > pcLeft)) {
                            if (rsn.get(i).get(j) == 0) {
                                if (queryString.substring(0, mGramLength - 1).equals(mBaseString.substring(Rn.get(i).get(j).getmLow() - mGramLength + pcLeft + 2,
                                        Rn.get(i).get(j).getmLow() - (Rn.get(i).get(j).getmLow() - mGramLength + pcLeft + 2) + 1)
                                        + a.substring(0, pcLeft))) {
                                    int pB = Rn.get(i).get(j).getmLow() - mGramLength + pcLeft + 2;
                                    int flag = 0;
                                    for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                        if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                            flag = k;
                                            break;
                                        }
                                    }
                                    int num = 0;
                                    for (int k = 0; k < flag; k++) {
                                        if (mDeltas.get(i).get(k).getmType() == 0) {
                                            num = num + mDeltas.get(i).get(k).getmContent().length();
                                        } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                            num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                            //cout<<num;
                                        }
                                    }
                                    results.get(i).add(pB + num + 1);
                                }
                            } else if (rsn.get(i).get(j) == 1) {
                                if (queryString.substring(0, mGramLength - 2 + 1)
                                        .equals(
                                                mBaseString.substring(Rn.get(i).get(j).getmLow() - 1 - mGramLength + pcLeft + 2,
                                                        Rn.get(i).get(j).getmLow() - 1 - (Rn.get(i).get(j).getmLow() - 1 - mGramLength + pcLeft + 2) + 1)
                                                        + a.substring(0, pcLeft - 1 + 1))) {
                                    int pB = Rn.get(i).get(j).getmLow() - 1 - mGramLength + pcLeft + 2;
                                    int flag = 0;
                                    for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                        if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                            flag = k;
                                            break;
                                        }
                                    }
                                    int num = 0;
                                    for (int k = 0; k < flag; k++) {
                                        if (mDeltas.get(i).get(k).getmType() == 0) {
                                            num = num + mDeltas.get(i).get(k).getmContent().length();
                                        } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                            num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                            //cout<<num;
                                        }
                                    }
                                    results.get(i).add(pB + num);
                                }
                            }
                        } else if (pcRight != -1 && ((mGramLength - 1) > (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1))) {
                            if (Rn.get(i).get(j).getmType() == 3) {
                                int pos = Rn.get(i).get(j).getmHigh() + (mGramLength - 1 - (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) - 1;
                                if (queryString.substring(queryString.length() - mGramLength + 1, queryString.length() - 1 - (queryString.length() - mGramLength + 1) + 1).equals(a.substring(queryString.length() - 2 * mGramLength + 2 + pSub, a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1) +
                                        mBaseString.substring(Rn.get(i).get(j).getmHigh(), pos - Rn.get(i).get(j).getmHigh() + 1))) {
                                    int pB = Rn.get(i).get(j).getmLow(), flag = 0;
                                    for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                        if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                            flag = k;
                                            break;
                                        }
                                    }
                                    int num = 0;
                                    for (int k = 0; k < flag; k++) {
                                        if (mDeltas.get(i).get(k).getmType() == 0) {
                                            num = num + mDeltas.get(i).get(k).getmContent().length();
                                        } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                            num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                            //cout<<num;
                                        }
                                    }
                                    if (rsn.get(i).get(j) == 0) {
                                        results.get(i).add(pB + num + pcRight + 1);
                                    } else {
                                        results.get(i).add(pB + num + pcRight);
                                    }


                                }
                            } else if (Rn.get(i).get(j).getmType() == 4) {
                                int pos = Rn.get(i).get(j).getmHigh() + 1 + (mGramLength - 1 - (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) - 1;
                                if (queryString.substring(queryString.length() - mGramLength + 1, queryString.length() - 1 - (queryString.length() - mGramLength + 1) + 1).equals(a.substring(queryString.length() - 2 * mGramLength + 2 + pSub, a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)
                                        + mBaseString.substring(Rn.get(i).get(j).getmHigh() + 1, pos - (Rn.get(i).get(j).getmHigh() + 1) + 1))) {
                                    int pB = Rn.get(i).get(j).getmLow(), flag = 0;
                                    for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                        if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                            flag = k;
                                            break;
                                        }
                                    }
                                    int num = 0;
                                    for (int k = 0; k < flag; k++) {
                                        if (mDeltas.get(i).get(k).getmType() == 0) {
                                            num = num + mDeltas.get(i).get(k).getmContent().length();
                                        } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                            num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                            //cout<<num;
                                        }
                                    }
                                    if (rsn.get(i).get(j) == 0) {
                                        results.get(i).add(pB + num + pcRight + 1);
                                    } else {
                                        results.get(i).add(pB + num + pcRight);
                                    }
                                }
                            }
                        } else if (((mGramLength - 1) > (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) && (mGramLength - 1) > pSub) {
                            if (rsn.get(i).get(j) == 0) {
                                if (queryString.substring(0, mGramLength - 1).equals(mBaseString.substring(Rn.get(i).get(j).getmLow() - mGramLength + pSub + 2, Rn.get(i).get(j).getmLow() - (Rn.get(i).get(j).getmLow() - mGramLength + pSub + 2) + 1) + a.substring(0, pSub))) {
                                    if (Rn.get(i).get(j).getmType() == 3) {
                                        int pos = Rn.get(i).get(j).getmHigh() + (mGramLength - 1 - (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) - 1;
                                        if (queryString.substring(queryString.length() - mGramLength + 1, queryString.length() - 1 - (queryString.length() - mGramLength + 1) + 1).equals(a.substring(queryString.length() - 2 * mGramLength + 2 + pSub, a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1) + mBaseString.substring(Rn.get(i).get(j).getmHigh(), pos - Rn.get(i).get(j).getmHigh() + 1))) {
                                            int pB = Rn.get(i).get(j).getmLow() - mGramLength + pSub + 2;
                                            int flag = 0;
                                            for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                                if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                                    flag = k;
                                                    break;
                                                }
                                            }
                                            int num = 0;
                                            for (int k = 0; k < flag; k++) {
                                                if (mDeltas.get(i).get(k).getmType() == 0) {
                                                    num = num + mDeltas.get(i).get(k).getmContent().length();
                                                } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                                    num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                                    //cout<<num;
                                                }
                                            }

                                            results.get(i).add(pB + num + 1);

                                        }
                                    } else if (Rn.get(i).get(j).getmType() == 4) {
                                        int pos = Rn.get(i).get(j).getmHigh() + 1 + (mGramLength - 1 - (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) - 1;
                                        if (queryString.substring(queryString.length() - mGramLength + 1, queryString.length() - 1 - (queryString.length() - mGramLength + 1) + 1).equals(a.substring(queryString.length() - 2 * mGramLength + 2 + pSub, a.length()
                                                - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1) +
                                                mBaseString.substring(Rn.get(i).get(j).getmHigh() + 1, pos - (Rn.get(i).get(j).getmHigh() + 1) + 1))) {
                                            int pB = Rn.get(i).get(j).getmLow() - mGramLength + pSub + 2;
                                            int flag = 0;
                                            for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                                if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                                    flag = k;
                                                    break;
                                                }
                                            }
                                            int num = 0;
                                            for (int k = 0; k < flag; k++) {
                                                if (mDeltas.get(i).get(k).getmType() == 0) {
                                                    num = num + mDeltas.get(i).get(k).getmContent().length();
                                                } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                                    num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                                    //cout<<num;
                                                }
                                            }

                                            results.get(i).add(pB + num + 1);
                                        }
                                    }
                                }
                            } else if (rsn.get(i).get(j) == 1) {

                                if (queryString.substring(0, mGramLength - 2 + 1).equals(mBaseString.substring(Rn.get(i).get(j).getmLow() - 1 - mGramLength + pSub + 2,
                                        Rn.get(i).get(j).getmLow() - 1 - (Rn.get(i).get(j).getmLow() - 1 - mGramLength + pSub + 2) + 1)
                                        + a.substring(0, pSub - 1 + 1))) {
                                    if (Rn.get(i).get(j).getmType() == 3) {
                                        //cout<<"hhhhhhhhhhhhhhhhhhhhhhhhh"<<endl;
                                        int pos = Rn.get(i).get(j).getmHigh() + (mGramLength - 1 - (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) - 1;
                                        if (queryString.substring(queryString.length() - mGramLength + 1, queryString.length() - 1 - (queryString.length() - mGramLength + 1) + 1).equals(a.substring(queryString.length() - 2 * mGramLength + 2 + pSub, a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1) + mBaseString.substring(Rn.get(i).get(j).getmHigh(), pos - Rn.get(i).get(j).getmHigh() + 1))) {
                                            int pB = Rn.get(i).get(j).getmLow() - 1 - mGramLength + pSub + 2;
                                            int flag = 0;
                                            for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                                if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                                    flag = k;
                                                    break;
                                                }
                                            }
                                            int num = 0;
                                            for (int k = 0; k < flag; k++) {
                                                if (mDeltas.get(i).get(k).getmType() == 0) {
                                                    num = num + mDeltas.get(i).get(k).getmContent().length();
                                                } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                                    num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                                    //cout<<num;
                                                }
                                            }

                                            results.get(i).add(pB + num);
                                        }
                                    } else if (Rn.get(i).get(j).getmType() == 4) {
                                        int pos = Rn.get(i).get(j).getmHigh() + 1 + (mGramLength - 1 - (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) - 1;
                                        if (queryString.substring(queryString.length() - mGramLength + 1, queryString.length() - 1 - (queryString.length() - mGramLength + 1) + 1).equals(a.substring(queryString.length() - 2 * mGramLength + 2 + pSub, a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1) + mBaseString.substring(Rn.get(i).get(j).getmHigh() + 1, pos - (Rn.get(i).get(j).getmHigh() + 1) + 1))) {
                                            int pB = Rn.get(i).get(j).getmLow() - 1 - mGramLength + pSub + 2;
                                            int flag = 0;
                                            for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                                if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                                    flag = k;
                                                    break;
                                                }
                                            }
                                            int num = 0;
                                            for (int k = 0; k < flag; k++) {
                                                if (mDeltas.get(i).get(k).getmType() == 0) {
                                                    num = num + mDeltas.get(i).get(k).getmContent().length();
                                                } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                                    num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                                    //cout<<num;
                                                }
                                            }
                                            results.get(i).add(pB + num);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // 搜索 小的 增量子串算法 TODO
    private void searchIncrMin(String queryString, List<Set<Integer>> results) {
        List<List<Node>> Rn = new ArrayList<>();
        List<List<Integer>> rsn = new ArrayList<>();
        //int deltanum = m_deltas.size();
        for (int i = 0; i < mDeltas.size(); i++) {
            List<Node> R = new ArrayList<>();
            List<Integer> rStart = new ArrayList<>();
            //int deltanum = m_deltas[i].size();
            for (int j = 0; j < mDeltas.get(i).size(); j++) {
                if (R.size() == 0) {
                    if (mDeltas.get(i).get(j).getmType() == 0) {
                        Node r = new Node(
                                mDeltas.get(i).get(j).getmHigh(),
                                mDeltas.get(i).get(j).getmLow(),
                                mDeltas.get(i).get(j).getmContent(),
                                mDeltas.get(i).get(j).getmType()
                        );
                        if (r.getmLow() + 1 < mGramLength) {
                            r.setmContent(mBaseString.substring(0, r.getmLow() + 1) + mDeltas.get(i).get(j).getmContent());
                            r.setmLow(-1);
                        }
                        r.setmType(3);
                        R.add(r);
                        rStart.add(0);
                    } else if (mDeltas.get(i).get(j).getmType() == 2) {
                        Node r = new Node(
                                mDeltas.get(i).get(j).getmLow(),
                                mDeltas.get(i).get(j).getmHigh(),
                                mDeltas.get(i).get(j).getmContent(),
                                mDeltas.get(i).get(j).getmType()
                        );
                        if (r.getmLow() < mGramLength) {
                            r.setmContent(mBaseString.substring(0, r.getmLow()) + mDeltas.get(i).get(j).getmContent());
                            r.setmLow(0);
                        }
                        r.setmType(4);
                        R.add(r);
                        rStart.add(1);
                    } else if (mDeltas.get(i).get(j).getmType() == 1) {
                        if (mDeltas.get(i).get(j).getmLow() < mGramLength) {
                            Node r = new Node(
                                    0,
                                    mDeltas.get(i).get(j).getmHigh(),
                                    mBaseString.substring(0, mDeltas.get(i).get(j).getmLow()),
                                    4
                            );
                            R.add(r);
                            rStart.add(1);
                        }
                    }
                } else {
                    int rTop = R.size() - 1;
                    if (mDeltas.get(i).get(j).getmType() == 0) {
                        Node r = new Node(
                                mDeltas.get(i).get(j).getmHigh(),
                                mDeltas.get(i).get(j).getmLow(),
                                mDeltas.get(i).get(j).getmContent(),
                                mDeltas.get(i).get(j).getmType()
                        );
                        if (R.get(rTop).getmType() == 3) {
                            int flag = 0;
                            int a = 0;
                            if (r.getmLow() - R.get(rTop).getmHigh() + 1 < mGramLength) {
                                if (r.getmLow() - R.get(rTop).getmHigh() + 1 == 0) {
                                    r.setmContent(R.get(rTop).getmContent() + mDeltas.get(i).get(j).getmContent());
                                } else {
                                    r.setmContent(R.get(rTop).getmContent() +
                                            mBaseString.substring(R.get(rTop).getmHigh(), r.getmLow() - R.get(rTop).getmHigh() + 1)
                                            + mDeltas.get(i).get(j).getmContent());
                                }
                                r.setmLow(R.get(rTop).getmLow());
                                flag = 1;
                                R.remove(R.size() - 1);
                                a = rStart.get(rStart.size() - 1);
                                rStart.remove(rStart.size() - 1);
                            }
                            r.setmType(3);
                            R.add(r);
                            if (flag == 0) {
                                rStart.add(0);
                            } else {
                                rStart.add(a);
                            }
                        } else if (R.get(rTop).getmType() == 4) {
                            int flag = 0;
                            int a = 0;
                            if (r.getmLow() - R.get(rTop).getmHigh() < mGramLength) {
                                if (r.getmLow() - R.get(rTop).getmHigh() == 0) {
                                    r.setmContent(R.get(rTop).getmContent() + mDeltas.get(i).get(j).getmContent());
                                } else {
                                    r.setmContent(R.get(rTop).getmContent() +
                                            mBaseString.substring(R.get(rTop).getmHigh() + 1, r.getmLow() - R.get(rTop).getmHigh())
                                            + mDeltas.get(i).get(j).getmContent());
                                }
                                r.setmLow(R.get(rTop).getmLow());
                                flag = 1;
                                R.remove(R.size() - 1);
                                a = rStart.get(rStart.size() - 1);
                                rStart.remove(rStart.size() - 1);
                            }
                            r.setmType(3);
                            R.add(r);
                            if (flag == 0) {
                                rStart.add(0);
                            } else {
                                rStart.add(a);
                            }
                        }
                    } else if (mDeltas.get(i).get(j).getmType() == 2) {
                        Node r = new Node(
                                mDeltas.get(i).get(j).getmLow(),
                                mDeltas.get(i).get(j).getmHigh(),
                                mDeltas.get(i).get(j).getmContent(),
                                mDeltas.get(i).get(j).getmType()
                        );
                        if (R.get(rTop).getmType() == 3) {
                            int flag = 0;
                            int a = 0;
                            if (r.getmLow() - R.get(rTop).getmHigh() < mGramLength) {
                                if (r.getmLow() - R.get(rTop).getmHigh() == 0) {
                                    r.setmContent(R.get(rTop).getmContent() + mDeltas.get(i).get(j).getmContent());
                                } else {
                                    r.setmContent(R.get(rTop).getmContent() +
                                            mBaseString.substring(R.get(rTop).getmHigh(), r.getmLow() - R.get(rTop).getmHigh())
                                            + mDeltas.get(i).get(j).getmContent());
                                }
                                r.setmLow(R.get(rTop).getmLow());
                                flag = 1;
                                R.remove(R.size() - 1);
                                a = rStart.get(rStart.size() - 1);
                                rStart.remove(rStart.size() - 1);
                            }
                            r.setmType(4);
                            R.add(r);
                            if (flag == 0) {
                                rStart.add(0);
                            } else {
                                rStart.add(a);
                            }

                        } else if (R.get(rTop).getmType() == 4) {
                            int flag = 0;
                            int a = 0;
                            if (r.getmLow() - R.get(rTop).getmHigh() - 1 < mGramLength) {
                                if (r.getmLow() - R.get(rTop).getmHigh() - 1 == 0) {
                                    r.setmContent(R.get(rTop).getmContent() + mDeltas.get(i).get(j).getmContent());
                                } else {
                                    r.setmContent(R.get(rTop).getmContent() +
                                            mBaseString.substring(R.get(rTop).getmHigh() + 1, r.getmLow() - R.get(rTop).getmHigh() - 1)
                                            + mDeltas.get(i).get(j).getmContent());
                                }
                                r.setmLow(R.get(rTop).getmLow());
                                flag = 1;
                                R.remove(R.size() - 1);
                                a = rStart.get(rStart.size() - 1);
                                rStart.remove(rStart.size() - 1);
                            }
                            r.setmType(4);
                            R.add(r);
                            if (flag == 0) {
                                rStart.add(0);
                            } else {
                                rStart.add(a);
                            }

                        }

                    } else if (mDeltas.get(i).get(j).getmType() == 1) {
                        if (R.get(rTop).getmType() == 3) {
                            int flag = 0;
                            if (mDeltas.get(i).get(j).getmLow() - R.get(rTop).getmHigh() < mGramLength) {
                                if (mDeltas.get(i).get(j).getmLow() - R.get(rTop).getmHigh() != 0) {
                                    R.get(rTop).setmContent(R.get(rTop).getmContent() +
                                            mBaseString.substring(R.get(rTop).getmHigh(), mDeltas.get(i).get(j).getmLow() - R.get(rTop).getmHigh()));
                                } else {
                                    // TODO 这块有毛病
                                    R.get(rTop).setmContent(R.get(rTop).getmContent());
//                                    R[rTop].m_content = R[rTop].m_content;
                                }
                                R.get(rTop).setmHigh(mDeltas.get(i).get(j).getmHigh());
                                flag = 1;
                                R.get(rTop).setmType(4);
                            }
                        } else if (R.get(rTop).getmType() == 4) {
                            int flag = 0;
                            if (mDeltas.get(i).get(j).getmLow() - R.get(rTop).getmHigh() - 1 < mGramLength) {
                                if (mDeltas.get(i).get(j).getmLow() - R.get(rTop).getmHigh() - 1 != 0) {
                                    R.get(rTop).setmContent(R.get(rTop).getmContent() +
                                            mBaseString.substring(R.get(rTop).getmHigh() + 1,
                                                    mDeltas.get(i).get(j).getmLow() - R.get(rTop).getmHigh() - 1));
                                } else {
                                    R.get(rTop).setmContent(R.get(rTop).getmContent());
                                }
                                R.get(rTop).setmHigh(mDeltas.get(i).get(j).getmHigh());
                                flag = 1;
                                R.get(rTop).setmHigh(4);
                            }
                        }
                    }
                }
            }
            Rn.add(R);
            rsn.add(rStart);
        }
    /*for(int i=0; i<Rn.size(); i++)
        for(int j=0; j<Rn[i].size(); j++)
        {
            cout<<Rn[i][j].m_content<<endl<<Rn[i][j].m_low<<endl<<Rn[i][j].m_high<<endl<<Rn[i][j].m_type<<endl;
        }
    for(int i=0; i<rsn.size(); i++)
        for(int j=0; j<rsn[i].size(); j++)
        {
            cout<<"rssssssn:"<<rsn[i][j]<<endl;
        }*/
    /*for(int i=0;i<R.size();i++)
    {
        cout<<R[i].m_content<<endl<<R[i].m_low<<endl<<R[i].m_high<<endl<<R[i].m_type<<endl;
    }*/

        int deltaNum = mDeltas.size();
        for (int i = 0; i < deltaNum; i++) {
            for (int j = 0; j < Rn.get(i).size(); j++) {
                if (Rn.get(i).get(j).getmContent().length() >= (queryString.length() - 2 * (mGramLength - 1))) {
                    String a = Rn.get(i).get(j).getmContent();
                    int pSub = a.indexOf(queryString.substring(mGramLength - 1,
                            queryString.length() - mGramLength - (mGramLength - 1) + 1));
                    //cout<<"aaaaaaaaaaaaaaaaaaaaaaaaaa------------------------"<<endl;
                    if (pSub != -1) {
                        int p = a.indexOf(queryString);
                        int pcLeft = a.indexOf(queryString.substring(mGramLength - 1,
                                queryString.length() - (mGramLength - 1)));
                        //cout<<"bbbbbbbbbbbbbbbb------------------------"<<endl;
                        int pcRight = a.indexOf(queryString.substring(0, queryString.length() - (mGramLength - 1)));
                        //cout<<"cccccccccccccccccccccccc-----------------------"<<endl;
                        if (p != -1) {
                            int pB = Rn.get(i).get(j).getmLow(), flag = 0;

                            for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                    flag = k;
                                    break;
                                }
                            }
                            int num = 0;
                            for (int k = 0; k < flag; k++) {
                                if (mDeltas.get(i).get(k).getmType() == 0) {
                                    num = num + mDeltas.get(i).get(k).getmContent().length();
                                } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                    num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                    //cout<<num;
                                }
                            }
                            if (rsn.get(i).get(j) == 0) {
                                results.get(i).add(pB + num + p + 1);
                            } else {
                                results.get(i).add(pB + num + p);
                            }

                        } else if (pcLeft != -1 && ((mGramLength - 1) > pcLeft)) {
                            if (rsn.get(i).get(j) == 0) {
                                if (queryString.substring(0, mGramLength - 1).equals(mBaseString.substring(Rn.get(i).get(j).getmLow() - mGramLength + pcLeft + 2,
                                        Rn.get(i).get(j).getmLow() - (Rn.get(i).get(j).getmLow() - mGramLength + pcLeft + 2) + 1)
                                        + a.substring(0, pcLeft))) {
                                    int pB = Rn.get(i).get(j).getmLow() - mGramLength + pcLeft + 2;
                                    int flag = 0;
                                    for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                        if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                            flag = k;
                                            break;
                                        }
                                    }
                                    int num = 0;
                                    for (int k = 0; k < flag; k++) {
                                        if (mDeltas.get(i).get(k).getmType() == 0) {
                                            num = num + mDeltas.get(i).get(k).getmContent().length();
                                        } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                            num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                            //cout<<num;
                                        }
                                    }
                                    results.get(i).add(pB + num + 1);
                                }
                            } else if (rsn.get(i).get(j) == 1) {
                                if (queryString.substring(0, mGramLength - 2 + 1)
                                        .equals(
                                                mBaseString.substring(Rn.get(i).get(j).getmLow() - 1 - mGramLength + pcLeft + 2,
                                                        Rn.get(i).get(j).getmLow() - 1 - (Rn.get(i).get(j).getmLow() - 1 - mGramLength + pcLeft + 2) + 1)
                                                        + a.substring(0, pcLeft - 1 + 1))) {
                                    int pB = Rn.get(i).get(j).getmLow() - 1 - mGramLength + pcLeft + 2;
                                    int flag = 0;
                                    for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                        if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                            flag = k;
                                            break;
                                        }
                                    }
                                    int num = 0;
                                    for (int k = 0; k < flag; k++) {
                                        if (mDeltas.get(i).get(k).getmType() == 0) {
                                            num = num + mDeltas.get(i).get(k).getmContent().length();
                                        } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                            num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                            //cout<<num;
                                        }
                                    }
                                    results.get(i).add(pB + num);
                                }
                            }
                        } else if (pcRight != -1 && ((mGramLength - 1) > (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1))) {
                            if (Rn.get(i).get(j).getmType() == 3) {
                                int pos = Rn.get(i).get(j).getmHigh() + (mGramLength - 1 - (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) - 1;
                                if (queryString.substring(queryString.length() - mGramLength + 1, queryString.length() - 1 - (queryString.length() - mGramLength + 1) + 1).equals(a.substring(queryString.length() - 2 * mGramLength + 2 + pSub, a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1) +
                                        mBaseString.substring(Rn.get(i).get(j).getmHigh(), pos - Rn.get(i).get(j).getmHigh() + 1))) {
                                    int pB = Rn.get(i).get(j).getmLow(), flag = 0;
                                    for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                        if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                            flag = k;
                                            break;
                                        }
                                    }
                                    int num = 0;
                                    for (int k = 0; k < flag; k++) {
                                        if (mDeltas.get(i).get(k).getmType() == 0) {
                                            num = num + mDeltas.get(i).get(k).getmContent().length();
                                        } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                            num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                            //cout<<num;
                                        }
                                    }
                                    if (rsn.get(i).get(j) == 0) {
                                        results.get(i).add(pB + num + pcRight + 1);
                                    } else {
                                        results.get(i).add(pB + num + pcRight);
                                    }


                                }
                            } else if (Rn.get(i).get(j).getmType() == 4) {
                                int pos = Rn.get(i).get(j).getmHigh() + 1 + (mGramLength - 1 - (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) - 1;
                                if (queryString.substring(queryString.length() - mGramLength + 1, queryString.length() - 1 - (queryString.length() - mGramLength + 1) + 1).equals(a.substring(queryString.length() - 2 * mGramLength + 2 + pSub, a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)
                                        + mBaseString.substring(Rn.get(i).get(j).getmHigh() + 1, pos - (Rn.get(i).get(j).getmHigh() + 1) + 1))) {
                                    int pB = Rn.get(i).get(j).getmLow(), flag = 0;
                                    for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                        if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                            flag = k;
                                            break;
                                        }
                                    }
                                    int num = 0;
                                    for (int k = 0; k < flag; k++) {
                                        if (mDeltas.get(i).get(k).getmType() == 0) {
                                            num = num + mDeltas.get(i).get(k).getmContent().length();
                                        } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                            num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                            //cout<<num;
                                        }
                                    }
                                    if (rsn.get(i).get(j) == 0) {
                                        results.get(i).add(pB + num + pcRight + 1);
                                    } else {
                                        results.get(i).add(pB + num + pcRight);
                                    }
                                }
                            }
                        } else if (((mGramLength - 1) > (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) && (mGramLength - 1) > pSub) {
                            if (rsn.get(i).get(j) == 0) {
                                if (queryString.substring(0, mGramLength - 1).equals(mBaseString.substring(Rn.get(i).get(j).getmLow() - mGramLength + pSub + 2, Rn.get(i).get(j).getmLow() - (Rn.get(i).get(j).getmLow() - mGramLength + pSub + 2) + 1) + a.substring(0, pSub))) {
                                    if (Rn.get(i).get(j).getmType() == 3) {
                                        int pos = Rn.get(i).get(j).getmHigh() + (mGramLength - 1 - (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) - 1;
                                        if (queryString.substring(queryString.length() - mGramLength + 1, queryString.length() - 1 - (queryString.length() - mGramLength + 1) + 1).equals(a.substring(queryString.length() - 2 * mGramLength + 2 + pSub, a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1) + mBaseString.substring(Rn.get(i).get(j).getmHigh(), pos - Rn.get(i).get(j).getmHigh() + 1))) {
                                            int pB = Rn.get(i).get(j).getmLow() - mGramLength + pSub + 2;
                                            int flag = 0;
                                            for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                                if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                                    flag = k;
                                                    break;
                                                }
                                            }
                                            int num = 0;
                                            for (int k = 0; k < flag; k++) {
                                                if (mDeltas.get(i).get(k).getmType() == 0) {
                                                    num = num + mDeltas.get(i).get(k).getmContent().length();
                                                } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                                    num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                                    //cout<<num;
                                                }
                                            }

                                            results.get(i).add(pB + num + 1);

                                        }
                                    } else if (Rn.get(i).get(j).getmType() == 4) {
                                        int pos = Rn.get(i).get(j).getmHigh() + 1 + (mGramLength - 1 - (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) - 1;
                                        if (queryString.substring(queryString.length() - mGramLength + 1, queryString.length() - 1 - (queryString.length() - mGramLength + 1) + 1).equals(a.substring(queryString.length() - 2 * mGramLength + 2 + pSub, a.length()
                                                - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1) +
                                                mBaseString.substring(Rn.get(i).get(j).getmHigh() + 1, pos - (Rn.get(i).get(j).getmHigh() + 1) + 1))) {
                                            int pB = Rn.get(i).get(j).getmLow() - mGramLength + pSub + 2;
                                            int flag = 0;
                                            for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                                if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                                    flag = k;
                                                    break;
                                                }
                                            }
                                            int num = 0;
                                            for (int k = 0; k < flag; k++) {
                                                if (mDeltas.get(i).get(k).getmType() == 0) {
                                                    num = num + mDeltas.get(i).get(k).getmContent().length();
                                                } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                                    num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                                    //cout<<num;
                                                }
                                            }

                                            results.get(i).add(pB + num + 1);
                                        }
                                    }
                                }
                            } else if (rsn.get(i).get(j) == 1) {

                                if (queryString.substring(0, mGramLength - 2 + 1).equals(mBaseString.substring(Rn.get(i).get(j).getmLow() - 1 - mGramLength + pSub + 2,
                                        Rn.get(i).get(j).getmLow() - 1 - (Rn.get(i).get(j).getmLow() - 1 - mGramLength + pSub + 2) + 1)
                                        + a.substring(0, pSub - 1 + 1))) {
                                    if (Rn.get(i).get(j).getmType() == 3) {
                                        //cout<<"hhhhhhhhhhhhhhhhhhhhhhhhh"<<endl;
                                        int pos = Rn.get(i).get(j).getmHigh() + (mGramLength - 1 - (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) - 1;
                                        if (queryString.substring(queryString.length() - mGramLength + 1, queryString.length() - 1 - (queryString.length() - mGramLength + 1) + 1).equals(a.substring(queryString.length() - 2 * mGramLength + 2 + pSub, a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1) + mBaseString.substring(Rn.get(i).get(j).getmHigh(), pos - Rn.get(i).get(j).getmHigh() + 1))) {
                                            int pB = Rn.get(i).get(j).getmLow() - 1 - mGramLength + pSub + 2;
                                            int flag = 0;
                                            for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                                if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                                    flag = k;
                                                    break;
                                                }
                                            }
                                            int num = 0;
                                            for (int k = 0; k < flag; k++) {
                                                if (mDeltas.get(i).get(k).getmType() == 0) {
                                                    num = num + mDeltas.get(i).get(k).getmContent().length();
                                                } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                                    num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                                    //cout<<num;
                                                }
                                            }

                                            results.get(i).add(pB + num);
                                        }
                                    } else if (Rn.get(i).get(j).getmType() == 4) {
                                        int pos = Rn.get(i).get(j).getmHigh() + 1 + (mGramLength - 1 - (a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1)) - 1;
                                        if (queryString.substring(queryString.length() - mGramLength + 1, queryString.length() - 1 - (queryString.length() - mGramLength + 1) + 1).equals(a.substring(queryString.length() - 2 * mGramLength + 2 + pSub, a.length() - 1 - (queryString.length() - 2 * mGramLength + 2 + pSub) + 1) + mBaseString.substring(Rn.get(i).get(j).getmHigh() + 1, pos - (Rn.get(i).get(j).getmHigh() + 1) + 1))) {
                                            int pB = Rn.get(i).get(j).getmLow() - 1 - mGramLength + pSub + 2;
                                            int flag = 0;
                                            for (int k = 0; k < mDeltas.get(i).size(); k++) {
                                                if (pB <= mDeltas.get(i).get(k).getmLow()) {
                                                    flag = k;
                                                    break;
                                                }
                                            }
                                            int num = 0;
                                            for (int k = 0; k < flag; k++) {
                                                if (mDeltas.get(i).get(k).getmType() == 0) {
                                                    num = num + mDeltas.get(i).get(k).getmContent().length();
                                                } else if (mDeltas.get(i).get(k).getmType() == 1) {
                                                    num = num - (mDeltas.get(i).get(k).getmHigh() - mDeltas.get(i).get(k).getmLow() + 1);
                                                    //cout<<num;
                                                }
                                            }
                                            results.get(i).add(pB + num);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private void minVerifyED(List<String> queryStrings) {
        if (mBaseString.isEmpty())
            return;
        int deltaNum = mDeltas.size();
        int queryNumber = queryStrings.size();
        List<Pair<Integer, BitSet>> array = new ArrayList<>();
        int minPos;
        int startPos = 0;
        int endPos = 0;
        mResultNum = 0;
        mResultDeltaNum = 0;
        long startTime = System.currentTimeMillis();

        for (int l = 0; l < queryNumber; l++) {
            List<Set<Integer>> results = new ArrayList<>();
            List<Set<Pair<Integer, Integer>>> endPosFilter = new ArrayList<>();
            System.out.println("-----results of query " + l + "-----");
            if (queryStrings.get(l).length() / (mThreshold + 1) < mGramLength) {
                System.out.println("gram length is too long ");
                return;
            }
            List<String> querySeg = new ArrayList<>();
            Tools.querySegment(queryStrings.get(l), querySeg, mThreshold + 1);
            //for(int i = 0; i< querySeg.size(); i++)
            //	cout<<querySeg[i]<<endl;
            int pos = 0;
            for (int segID = 0; segID < mThreshold + 1; segID++) {
                //cout<<"\t"<<"seg: "<<segID<<endl;
                List<Set<Pair<Integer, Integer>>> candidates = new ArrayList<>();
                minVerify2(querySeg.get(segID), candidates);
                //output_result_checkpair(candidates);

                for (int deltaID = 0; deltaID < deltaNum; deltaID++) {
                    //cout<<"delta: "<< deltaID<<endl;
                    for (Pair<Integer, Integer> pair : candidates.get(deltaID)) {
                        char[] chars = new char[queryStrings.get(l).length() + 2 * mThreshold];
                        Arrays.fill(chars, 'a');
                        String destString = new String(chars);
                        List tempList = generateSubstring(pair.getKey(), pos + pair.getValue(), destString, startPos, endPos, mThreshold, queryStrings.get(l).length(), deltaID);
                        startPos = (Integer) tempList.get(0);
                        endPos = (Integer) tempList.get(1);
                        destString = (String) tempList.get(2);
                        //cout<<destString<< " "<< startPos<<" "<<endPos<<endl;
                        if (endPosFilter.get(deltaID).contains(new Pair<>(startPos, endPos)))
                            continue;
                        endPosFilter.get(deltaID).add(new Pair<>(startPos, endPos));
                        String queryRight = queryStrings.get(l).substring(pos + querySeg.get(segID).length());
                        //cout<<"queryRight:"<< queryRight<<endl;
                        String destRight = destString.substring(pos + querySeg.get(segID).length() + mThreshold);
                        //cout<<"destRight:"<< destRight<<endl;
                        int right, matchPos = 0;
                        if ((right = subEDFixStart(destRight, queryRight, matchPos)) <= mThreshold) {
                            String queryLeft = queryStrings.get(l).substring(0, pos);
                            //cout<<"queryleft:"<< queryleft<<endl;
                            queryLeft = new StringBuilder(queryLeft).reverse().toString();
                            String destLeft = destString.substring(0, pos + mThreshold);
                            //cout<<"destleft:"<< destleft<<endl;
                            destLeft = new StringBuilder(destLeft).reverse().toString();

                            if (subEDFixStart(destLeft, queryLeft, matchPos) <= mThreshold - right) {
                                //cout<<"match:"<<matchPos<<endl;
                                //cout<<"pos:"<<pos<<endl;
                                int pB = startPos + mThreshold + pos - matchPos;
                                int flag = 0;
                                for (int j = 0; j < mDeltas.get(deltaID).size(); j++) {
                                    if (pB <= mDeltas.get(deltaID).get(j).getmLow()) {
                                        flag = j;
                                        break;
                                    }
                                }
                                int num = 0;
                                for (int j = 0; j < flag; j++) {
                                    if (mDeltas.get(deltaID).get(j).getmType() == 0) {
                                        num = num + mDeltas.get(deltaID).get(j).getmContent().length();
                                    } else if (mDeltas.get(deltaID).get(j).getmType() == 1) {
                                        num = num - (mDeltas.get(deltaID).get(j).getmHigh() - mDeltas.get(deltaID).get(j).getmLow() + 1);
                                    }
                                }
                                results.get(deltaID).add(pB + num);
                            }

                        }


                    }
                }
                pos += querySeg.get(segID).length();

            }
            //cout<<"---------------result---------------"<<endl;

            mResultDeltaNum = outputResult(results, mResultDeltaNum);
            mResultNum += getNum(results);
        }

        mQueryCost = System.currentTimeMillis() - startTime;
        outputCost();
    }


    private void minVerify(List<String> queryStrings) throws Exception {
        int length = mBaseString.length();
        if (length == 0) {
            return;
        }
        int queryNum = queryStrings.size();
        mResultNum = 0;
        mResultDeltaNum = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < queryNum; i++) {
            System.out.println("-----results of query " + i + "-----");
            List<Set<Integer>> results = new ArrayList<>();
            // 调用 重载函数
            minVerify(queryStrings.get(i), results);

            // 调用 search in min
            searchIncrMin(queryStrings.get(i), results);
            if (deleteFlag == 0 && changeFlag == 0) {
                mResultDeltaNum = outputResult(results, mResultDeltaNum);
                if (i == queryNum - 1) {
                    System.out.println("total searching reserved substring verify number: " + mVerifyNum);
                }
                mResultNum += getNum(results);
            } else if (deleteFlag == 1) {
                int deleteNum = 0;
                for (int j = 0; j < results.size(); j++) {
                    if (results.get(j).isEmpty()) continue;
                    mDeltas.get(j).clear();
                    deleteNum++;
                }
                saveDeltaIndex("index");
                System.out.println("clear delete delta number: " + deleteNum);
                System.out.println("m_deltas size: " + mDeltas.size());
            } else if (changeFlag == 1) {
                String update;
                // TODO 注意此文件路径
                File file = new File("update");
                if (!file.exists()) {
                    throw new Exception("文件不存在！");
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                update = reader.readLine();
                reader.close();
                //调用 计算编辑距离操作
                backtrackingPath(mBaseString, update);

                List<Node> cTemp = new ArrayList<>();
                while (!deltaC.empty()) {
                    Node a = deltaC.peek();
                    cTemp.add(a);
                    deltaC.pop();
                }
                int updateNum = 0;
                for (int j = 0; j < mDeltas.size(); j++) {
                    if (results.get(j).isEmpty()) continue;
                    mDeltas.get(j).clear();
                    for (int k = 0; k < cTemp.size(); k++) {
                        mDeltas.get(j).add(cTemp.get(k));
                    }
                    updateNum++;
                }
                saveDeltaIndex("index");
                System.out.println("update delta number: " + updateNum);

            }
        }
        if (deleteFlag == 0 && changeFlag == 0) {
            mQueryCost = System.currentTimeMillis() - startTime;
            outputCost();
        }

    }

    private void minVerify(String queryString, List<Set<Integer>> results) {
        int length = mBaseString.length();
        List<Pair<Integer, BitSet>> array;
        int minPos = 0;
        List<Integer> gramLists = new ArrayList<>();
        Tools.str2gramsPos(queryString, gramLists, mGramLength);
        int listSize = gramLists.size();
        List<List<Pair<Integer, BitSet>>> arrayLists = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            array = mGramInvertedList.getArray(gramLists.get(i));
            arrayLists.add(array);
        }
        if (mAnchorSpan == '\0') {
            minPos = Tools.minimize(arrayLists);
        }
        minPos = Tools.minimizeSpan(arrayLists);
        for (int i = 0; i < minPos; i++) {
            array = arrayLists.get(i);
            if (array == null) {
                continue;
            }
            int arraySize = array.size() - 2;
            for (int j = 0; j < arraySize; j++) {
                Pair<Integer, BitSet> gramPair = array.get(j);
                if (gramPair.getValue().empty()) {
                    break;
                }
                int posInB = gramPair.getKey();
                for (int deltaID = 0; deltaID < mDeltaNum; deltaID++) {
                    if (gramPair.getValue().test(deltaID * 2)) {
                        mVerifyNum++;
                        verifyOnSingleDelta(deltaID, posInB, i, queryString, results);
                    }
                }
            }
        }
        array = arrayLists.get(minPos);
        if (array != null) {
            int arraySize = array.size() - 2;
            for (int j = 0; j < arraySize; j++) {
                Pair<Integer, BitSet> gramPair = array.get(j);
                int posInB = gramPair.getKey();
                for (int deltaID = 0; deltaID < mDeltaNum; deltaID++) {
                    //cout<<"deltaID: "<<deltaID<<" posInB: "<<posInB <<" posInP: "<< minPos<<endl;
                    mVerifyNum++;
                    verifyOnSingleDelta(deltaID, posInB, minPos, queryString, results);
                }
            }
        }
        for (int i = minPos + 1; i < listSize; i++) {
            array = arrayLists.get(i);
            if (array != null) {
                continue;
            }
            int arraySize = array.size() - 2;
            for (int j = 0; j < arraySize; j++) {
                Pair<Integer, BitSet> gramPair = array.get(j);
                if (gramPair.getValue().empty()) {
                    break;
                }
                int posInB = gramPair.getKey();
                for (int deltaID = 0; deltaID < mDeltaNum; deltaID++) {
                    if (gramPair.getValue().test(deltaID * 2 + 1)) {
                        mVerifyNum++;
                        verifyOnSingleDelta(deltaID, posInB, i, queryString, results);
                    }
                }
            }
        }
    }

    private void minVerify2(String queryString, List<Set<Pair<Integer, Integer>>> results) {
        int length = mBaseString.length();

        List<Pair<Integer, BitSet>> array;
        int minPos;
        List<Integer> gramLists = new ArrayList<>();
        Tools.str2gramsPos(queryString, gramLists, mGramLength);
        int listSize = gramLists.size();
        List<List<Pair<Integer, BitSet>>> arrayLists = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            array = mGramInvertedList.getArray(gramLists.get(i));
            arrayLists.add(array);
        }
        if (mAnchorSpan != '\0')
            minPos = Tools.minimize(arrayLists);
        minPos = Tools.minimizeSpan(arrayLists);
        //cout<<minPos<<endl;
        //cout<<"left"<<endl;
        for (int i = 0; i < minPos; i++) {
            array = arrayLists.get(i);
            if (array == null) continue;
            int arraySize = array.size() - 2;
            for (int j = 0; j < arraySize; j++) {
                Pair<Integer, BitSet> gramPair = array.get(j);
                if (gramPair.getValue().empty()) break;
                int posInB = gramPair.getKey();
                for (int deltaID = 0; deltaID < mDeltaNum; deltaID++) {
                    if (gramPair.getValue().test(deltaID * 2)) {

                        //cout<<"deltaID: "<<deltaID<<" posInB: "<<posInB <<" posInP: "<< i<<endl;
                        mVerifyNum++;
                        verifyOnSingleDelta2(deltaID, posInB, i, queryString, results);
                    }
                }
            }
        }

        //cout<<"min"<<endl;
        {
            array = arrayLists.get(minPos);
            if (array != null) {

                int arraySize = array.size() - 2;
                for (int j = 0; j < arraySize; j++) {
                    Pair<Integer, BitSet> gramPair = array.get(j);
                    int posInB = gramPair.getKey();
                    for (int deltaID = 0; deltaID < mDeltaNum; deltaID++) {

                        //cout<<"deltaID: "<<deltaID<<" posInB: "<<posInB <<" posInP: "<< minPos<<endl;
                        mVerifyNum++;
                        verifyOnSingleDelta2(deltaID, posInB, minPos, queryString, results);
                    }
                }
            }
        }

        //cout<<"right"<<endl;
        for (int i = minPos + 1; i < listSize; i++) {
            array = arrayLists.get(i);
            if (array == null) continue;
            int arraySize = array.size() - 2;
            for (int j = 0; j < arraySize; j++) {
                Pair<Integer, BitSet> gramPair = array.get(j);
                if (gramPair.getValue().empty()) break;
                int posInB = gramPair.getKey();
                for (int deltaID = 0; deltaID < mDeltaNum; deltaID++) {
                    if (gramPair.getValue().test(deltaID * 2 + 1)) {
                        //cout<<"deltaID: "<<deltaID<<" posInB: "<<posInB <<" posInP: "<< i<<endl;
                        mVerifyNum++;
                        verifyOnSingleDelta2(deltaID, posInB, i, queryString, results);
                    }
                }
            }
        }
    }


    private void verifyOnSingleDelta(int deltaID, int posInB, int posInP, String pattern, List<Set<Integer>> results) {
        List<Node> array = this.mDeltas.get(deltaID);
        int startPos = 0, endPos = 0;
        int deltaPos = binarySearch(array, posInB);
        if (deltaPos >= 0 && bDestoriedGarm(array.get(deltaPos), posInB)) {
            return;
        }
        if ((endPos = verifyRightOnSingleDelta(array, deltaPos, posInB + mGramLength, posInP + mGramLength, pattern)) < 0) {
            return;
        }
        if (deltaPos == -1) {
            deltaPos = array.size();
        }
        if ((startPos = verifyLeftOnSingleDelta(array, deltaPos - 1, posInB - 1, posInP - 1, pattern)) < 0) {
            return;
        }
        int pB = startPos;
        int flag = 0;
        for (int i = 0; i < array.size(); i++) {
            if (pB < array.get(i).getmLow()) {
                flag = i;
                break;
            }
        }
        int num = 0;
        for (int i = 0; i < flag; i++) {
            if (array.get(i).getmType() == 0) {
                num = num + array.get(i).getmContent().length();
            } else if (array.get(i).getmType() == 1) {
                num = num - (array.get(i).getmHigh() - array.get(i).getmLow() + 1);
            }
        }
        results.get(deltaID).add(pB + num);
    }

    private void verifyOnSingleDelta2(int deltaID, int posInB, int posInP, String pattern, List<Set<Pair<Integer, Integer>>> results) {
        List<Node> array = mDeltas.get(deltaID);
        int startPos = 0, endPos = 0;
        int deltaPos = binarySearch(array, posInB);
        if (deltaPos >= 0 && bDestoriedGarm(array.get(deltaPos), posInB)) {
            return;
        }
        if ((endPos = verifyRightOnSingleDelta(array, deltaPos, posInB + mGramLength, posInP + mGramLength, pattern)) < 0) {
            return;
        }
        if (deltaPos == -1) {
            deltaPos = array.size();
        }
        if ((startPos = verifyLeftOnSingleDelta(array, deltaPos - 1, posInB - 1, posInP - 1, pattern)) < 0) {
            return;
        }
        results.get(deltaID).add(new Pair<>(posInB, posInP));

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

    public void outputResultCheckPair(List<Set<Pair<Integer, Integer>>> results) {
        for (int i = 0; i < results.size(); i++) {
            System.out.println("delta " + i + ": ");
            for (Pair pair : results.get(i)) {
                System.out.println(pair.getKey() + ":" + pair.getValue() + ",");
            }
            System.out.println();
        }
    }

    public void outputNum(List<Set<Integer>> results) {
        int total = 0;
        for (Set<Integer> result : results) {
            total += result.size();
        }
        System.out.println(total);
    }

    public int getDelFlag() {
        return deleteFlag;
    }

    public void setDelFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(int changeFlag) {
        this.changeFlag = changeFlag;
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
                if (mBaseString.substring(posInB, posInB + remain).compareTo(pattern.substring(posInP, posInP + remain)) != 0)
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
                        if (pattern.substring(posInP, posInP + length).compareTo(
                                array.get(deltaPos).getmContent().substring(0, remain)) != 0)
                            return -1;
                        posInP += length;
                        remain -= length;
                        break;
                    case Tools.DEL:
                        posInB = array.get(deltaPos).getmHigh() + 1;
                        break;
                    case Tools.SUB:
                        length = Math.min(length, remain);
                        if (pattern.substring(posInP, posInP + length).compareTo(
                                array.get(deltaPos).getmContent().substring(0, remain)) != 0)
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

    private int verifyLeftOnSingleDelta(List<Node> array, int deltaPos, int posInB, int posInP, String pattern) {
        int length;
        while (posInP >= 0) {
            if (deltaPos < 0) {
                if (posInB < posInP) {
                    return -1;
                }
                length = posInP + 1;
                if (mBaseString.substring(posInB - posInP, posInB - posInP + length).compareTo(pattern.substring(0, length)) != 0) {
                    return -1;
                }
                return posInB - posInP;
            } else if (posInP < posInB - array.get(deltaPos).getmHigh()) {
                length = posInP + 1;
                if (mBaseString.substring(posInB - posInP, posInB - posInP + length).compareTo(pattern.substring(0, length)) != 0) {
                    return -1;
                }
                return posInB - posInP;
            } else {
                length = posInB - array.get(deltaPos).getmHigh();
                if (mBaseString.substring(array.get(deltaPos).getmHigh() + 1, array.get(deltaPos).getmHigh() + 1 + length)
                        .compareTo(pattern.substring(posInP - length + 1, posInP + 1)) != 0) {
                    return -1;
                }
                posInB -= length;
                posInP -= length;
                length = array.get(deltaPos).getmContent().length();
                switch (array.get(deltaPos).getmType()) {
                    case Tools.INS:
                        length = Math.min(length, posInP + 1);
                        if (pattern.substring(posInP - length + 1, posInP + 1).compareTo(
                                array.get(deltaPos).getmContent().substring(array.get(deltaPos).getmContent().length() - length))
                                != 0)
                            return -1;
                        posInP -= length;
                        break;
                    case Tools.DEL:
                        posInB = array.get(deltaPos).getmLow() - 1;
                        break;
                    case Tools.SUB:
                        length = Math.min(length, posInP + 1);
                        if (pattern.substring(posInP - length + 1, posInP + 1).compareTo(
                                array.get(deltaPos).getmContent().substring(array.get(deltaPos).getmContent().length() - length))
                                != 0)
                            return -1;
                        posInP -= length;
                        posInB -= length;
                        break;
                }
                deltaPos--;
            }
        }
        return posInB - posInP;
    }

    public void backtrackingPath(String basic, String s) {
        int m = basic.length();
        int n = s.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) dp[i][0] = i;
        for (int i = 0; i < n + 1; i++) dp[0][i] = i;

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (basic.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }

        System.out.println("distance " + dp[m][n]);

        while (n >= 0 || m >= 0) {

            if (n != 0 && dp[m][n - 1] + 1 == dp[m][n]) {
                Node a = new Node(
                        m - 1,
                        m,
                        String.valueOf(s.charAt(n - 1)),
                        0
                );

                if (deltaC.size() == 0) {
                    deltaC.push(a);
                } else {
                    Node b = deltaC.peek();
                    if (b.getmType() == a.getmType() && b.getmLow() == a.getmLow()) {
                        a.setmContent(a.getmContent() + b.getmContent());
                        deltaC.pop();
                    }
                    deltaC.push(a);
                }
                //cout<<"insert "<<s[n-1]<<" at "<<m-1<<endl;
                n = n - 1;
                continue;
            } else if (m != 0 && dp[m - 1][n] + 1 == dp[m][n]) {
                //cout<<"delete "<<basic[m-1]<<" at "<<m-1<<endl;
                Node a = new Node(
                        m - 1,
                        m - 1,
                        "-",
                        1
                );
                if (deltaC.size() == 0) {
                    deltaC.push(a);
                } else {
                    Node b = deltaC.peek();
                    if (b.getmType() == a.getmType() && b.getmLow() == a.getmLow() + 1) {
                        a.setmHigh(b.getmHigh());
                        deltaC.pop();
                    }
                    deltaC.push(a);
                }
                m = m - 1;
                continue;
            } else if (m != 0 && n != 0 && dp[m - 1][n - 1] + 1 == dp[m][n]) {
                //cout<<"replace "<<basic[m-1]<<" to "<<s[n-1]<<" at "<<m-1<<endl;
                Node a = new Node(
                        m - 1,
                        m - 1,
                        String.valueOf(s.charAt(n - 1)),
                        2
                );
                if (deltaC.size() == 0) {
                    deltaC.push(a);
                } else {
                    Node b = deltaC.peek();
                    if (b.getmType() == a.getmType() && b.getmLow() == a.getmLow() + 1) {
                        a.setmHigh(b.getmHigh());
                        a.setmContent(a.getmContent() + b.getmContent());
                        deltaC.pop();
                    }
                    deltaC.push(a);
                }
                n = n - 1;
                m = m - 1;
                continue;
            }
            n = n - 1;
            m = m - 1;
        }

    }

    //  TODO  C++函数可以传递引用 java 是值传递 这块需要改上面的函数
    private List generateSubstring(int posInB, int posInP, String destString, int startPos, int endPos, int threshold, int queryLen, int deltaID) {
        List list = new ArrayList();
        destString = stringReplace(destString, posInP + threshold, mGramLength, mBaseString, posInB, mGramLength);
        List<Node> array = mDeltas.get(deltaID);
        int deltaPos = binarySearch(array, posInB);
        Pair<Integer, String> pairEndPos = generateRightSubstring(array, deltaPos, posInB + mGramLength, posInP + mGramLength, destString, threshold, queryLen);
        endPos = pairEndPos.getKey();
        destString = pairEndPos.getValue();
        if (deltaPos == -1) deltaPos = array.size();
        Pair<Integer, String> pairStartPos = generateLeftSubstring(array, deltaPos - 1, posInB - 1, posInP - 1, destString, threshold, queryLen);
        startPos = pairStartPos.getKey();
        destString = pairStartPos.getValue();
        list.add(startPos);
        list.add(endPos);
        list.add(destString);
        return list;
    }

    private Pair<Integer, String> generateRightSubstring(List<Node> array, int deltaPos, int posInB, int posInP, String destString, int threshold, int queryLen) {
        int remain = queryLen - posInP + threshold;
        int length;
        posInP += threshold;
        while (remain != 0) {
            if (deltaPos < 0 || deltaPos >= array.size()) {
                destString = stringReplace(destString, posInP, remain, mBaseString, posInB, remain);
                return new Pair<>(posInB + remain - 1, destString);
            } else if (array.get(deltaPos).getmLow() > posInB + remain - 1) {
                destString = stringReplace(destString, posInP, remain, mBaseString, posInB, remain);
                return new Pair<>(posInB + remain - 1, destString);
            } else {
                length = array.get(deltaPos).getmLow() - posInB;
                destString = stringReplace(destString, posInP, length, mBaseString, posInB, length);
                posInB += length;
                posInP += length;
                remain -= length;
                length = array.get(deltaPos).getmContent().length();
                switch (array.get(deltaPos).getmType()) {
                    case Tools.INS:
                        length = Math.min(length, remain);
                        destString = stringReplace(destString, posInP, length, array.get(deltaPos).getmContent(), 0, length);
                        posInP += length;
                        remain -= length;
                        break;
                    case Tools.DEL:
                        posInB = array.get(deltaPos).getmHigh() + 1;
                        break;
                    case Tools.SUB:
                        length = Math.min(length, remain);
                        destString = stringReplace(destString, posInP, length, array.get(deltaPos).getmContent(), 0, length);
                        posInP += length;
                        posInB += length;
                        remain -= length;
                        break;
                }
                deltaPos++;
            }

        }
        return new Pair<>(posInB + remain - 1, destString);

    }

    private Pair<Integer, String> generateLeftSubstring(List<Node> array, int deltaPos, int posInB, int posInP, String destString, int threshold, int querylen) {
        int length;
        posInP += threshold;
        int remain = posInP + 1;

        while (remain != 0) {

            if (deltaPos < 0) {
                if (posInB < remain)
                    remain = posInB + 1;
                destString = stringReplace(destString, 0, remain, mBaseString, posInB - remain + 1, remain);
                return new Pair<>(posInB - posInP, destString);
            } else if (remain < posInB - array.get(deltaPos).getmHigh()) {
                destString = stringReplace(destString, 0, remain, mBaseString, posInB - remain + 1, remain);
                return new Pair<>(posInB - posInP, destString);
            } else {
                length = posInB - array.get(deltaPos).getmHigh();
                destString = stringReplace(destString, posInP - length + 1, length, mBaseString, array.get(deltaPos).getmHigh() + 1, length);
                posInB -= length;
                posInP -= length;
                remain -= length;
                length = array.get(deltaPos).getmContent().length();
                switch (array.get(deltaPos).getmType()) {
                    case Tools.INS:
                        length = Math.min(length, remain);
                        destString = stringReplace(destString, posInP - length + 1, length, array.get(deltaPos).getmContent(), array.get(deltaPos).getmContent().length() - length, length);
                        posInP -= length;
                        remain -= length;
                        break;
                    case Tools.DEL:
                        posInB = array.get(deltaPos).getmLow() - 1;
                        break;
                    case Tools.SUB:
                        length = Math.min(length, remain);
                        destString = stringReplace(destString, posInP - length + 1, length, array.get(deltaPos).getmContent(), array.get(deltaPos).getmContent().length() - length, length);
                        remain -= length;
                        posInP -= length;
                        posInB -= length;
                        break;
                }
                deltaPos--;
            }
        }
        return new Pair<>(posInB - posInP, destString);
    }

    public String stringReplace(String origin, int start1, int len1, String dest, int start2, int len2) {
        char[] chars = origin.toCharArray();
        String substring = dest.substring(start2, start2 + len2);
        for (int i = start1, j = 0; i < start1 + len1; i++, j++) {
            chars[i] = substring.charAt(j);
        }
        return new String(chars);
    }

    private int subEDFixStart(String source, String query, int pos) {
        int n = source.length();
        int m = query.length();
        pos = 0;
        if (m == 0)
            return 0;
        int result = m;
        int[] score = new int[m + 1];
        for (int i = 0; i <= m; i++)
            score[i] = i;
        int[] x = new int[2];
        int b = 0;
        for (int i = 1; i <= n; i++) {
            x[b] = i;
            for (int j = 1; j <= m; j++) {
                x[1 - b] = score[j - 1] + (source.charAt(i - 1) == query.charAt(j - 1) ? 0 : 1);
                x[1 - b] = Math.min(x[1 - b], x[b] + 1);
                x[1 - b] = Math.min(x[1 - b], score[j] + 1);
                score[j - 1] = x[b];
                b = 1 - b;
                //cout<< score[j-1]<<" ";
            }
            score[m] = x[b];
            if (result > score[m]) {
                result = score[m];
                pos = i;
            }
            //result = min(result, score[m]);
            //cout<<score[m]<<" ";
        }
        return result;
    }


}
