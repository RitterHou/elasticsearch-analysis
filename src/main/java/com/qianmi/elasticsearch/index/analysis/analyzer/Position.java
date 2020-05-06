package com.qianmi.elasticsearch.index.analysis.analyzer;

/**
 * @author hourui 2020/4/30 5:32 PM
 */
public class Position {

    private int value1;

    private int value2;

    Position(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public char[] parse(char[] charArray) {
        int length = charArray.length;
        while (value1 < 0) {
            value1 = length + value1;
        }
        while (value2 < 0) {
            value2 = length + value2;
        }

        int start = Math.min(value1, value2);
        int end = Math.max(value1, value2);

        if (start >= length) {
            start = 0;
        }
        if (end >= length) {
            end = length - 1;
        }

        char[] result = new char[end - start + 1];
        System.arraycopy(charArray, start, result, 0, result.length);
        return result;
    }

    @Override
    public String toString() {
        return "Position[" + value1 + ", " + value2 + "]";
    }

}
