package com.qianmi.elasticsearch.index.analysis.analyzer;

/**
 * @author hourui 2020/4/30 5:32 PM
 */
public class Position {

    private int start;

    private int end;

    /**
     * 创建一个Position对象，创建时保证小值为start，大值为end
     *
     * @param value1 值1
     * @param value2 值2
     */
    Position(int value1, int value2) {
        if (value1 > value2) {
            this.start = value2;
            this.end = value1;
        } else {
            this.start = value1;
            this.end = value2;
        }
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Position[" + start + ", " + end + "]";
    }

}
