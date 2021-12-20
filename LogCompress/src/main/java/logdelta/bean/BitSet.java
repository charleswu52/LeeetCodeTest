package logdelta.bean;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/12/19 下午7:16
 */
public class BitSet {

    // 自定义的 BitSet
    //TODO  后续可能需要调整这个方法
    private static int num = 0;
    private char[] buf;

    public BitSet() {
        int bufSize = (num >> 3) + ((num & 0x7) != 0 ? 1 : 0);
        this.buf = new char[bufSize];
        Arrays.fill(buf, (char) 0);
    }

    public BitSet(char[] buf) {
        int bufSize = (num >> 3) + ((num & 0x7) != 0 ? 1 : 0);
        this.buf = new char[bufSize];
        System.arraycopy(buf, 0, this.buf, 0, bufSize);
    }

    public BitSet(BitSet bitSet) {
        int bufSize = (num >> 3) + ((num & 0x7) != 0 ? 1 : 0);
        this.buf = new char[bufSize];
        System.arraycopy(bitSet.getBuf(), 0, this.buf, 0, bufSize);
    }

    public char[] getBuf() {
        return buf;
    }

    public boolean test(int x) {
        assert x < num;
        return !((this.getBuf()[x >> 3] & (1 << (x & 0x7))) == 0);
    }

    public void set(int x) {
        assert (x < num);
        buf[x >> 3] |= (1 << (x & 0x7));
    }

    public void clear(int x) {
        assert (x < num);
        buf[x >> 3] &= ~(1 << (x & 0x7));
    }

    public boolean empty() {
        int bufSize = (num >> 3) + ((num & 0x7) != 0 ? 1 : 0);
        for (int i = 0; i < bufSize; i++) {
            if (buf[i] != 0) {
                return false;
            }
        }
        return true;
    }

    // 序列化写文件
    public void write(BufferedWriter out) throws IOException {
        int bufSize = (num >> 3) + ((num & 0x7) != 0 ? 1 : 0);
        out.write(buf,0,bufSize);
        out.close();
    }

    public static void setNum(int n) {
        num = n;
    }

    public static int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "BitSet{" +
                "buf=" + Arrays.toString(buf) +
                '}';
    }
}
