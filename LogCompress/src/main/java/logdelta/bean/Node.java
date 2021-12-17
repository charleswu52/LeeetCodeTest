package logdelta.bean;

import java.util.Objects;

/**
 * @author WuChao
 * @create 2021/12/17 下午5:56
 */
public class Node {
    // 保存 delta节点的内部元素
    private int mLow;
    private int mHigh;
    private String mContent;
    private int mType;

    public Node() {
    }

    public Node(int mLow, int mHigh, String mContent, int mType) {
        this.mLow = mLow;
        this.mHigh = mHigh;
        this.mContent = mContent;
        this.mType = mType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return mLow == node.mLow && mHigh == node.mHigh && mType == node.mType && mContent.equals(node.mContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mLow, mHigh, mContent, mType);
    }

    @Override
    public String toString() {
        return "Node{" +
                "mLow=" + mLow +
                ", mHigh=" + mHigh +
                ", mContent='" + mContent + '\'' +
                ", mType=" + mType +
                '}';
    }

    public int getmLow() {
        return mLow;
    }

    public void setmLow(int mLow) {
        this.mLow = mLow;
    }

    public int getmHigh() {
        return mHigh;
    }

    public void setmHigh(int mHigh) {
        this.mHigh = mHigh;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }
}
