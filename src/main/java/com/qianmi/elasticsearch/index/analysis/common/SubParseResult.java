package com.qianmi.elasticsearch.index.analysis.common;

/**
 * 迫于Java不支持多个方法返回值，无奈的定义了一个Bean
 *
 * @author hourui 2020/5/7 11:39 AM
 */
public class SubParseResult {

    private final char[] chars;

    private final int start;

    private final int end;

    public SubParseResult(char[] chars, int start, int end) {
        this.chars = chars;
        this.start = start;
        this.end = end;
    }

    public char[] getChars() {
        return chars;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
