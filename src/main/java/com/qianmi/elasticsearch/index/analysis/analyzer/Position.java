package com.qianmi.elasticsearch.index.analysis.analyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author hourui 2020/4/30 5:32 PM
 */
public class Position {

    private static final Logger LOG = LogManager.getLogger();

    private final int value1;

    private final int value2;

    Position(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * 根据当前的position配置对输入进行解析
     *
     * @param charArray 输入的字符数组
     * @return 输出的字符数组
     */
    public SubParseResult parse(char[] charArray) {
        // TODO: 未对emoji做相应的处理
        int length = charArray.length;
        LOG.info("Parse char array size: {}", length);
        if (length == 0) {
            return new SubParseResult(charArray, 0, 0);
        }

        int v1 = value1;
        int v2 = value2;
        while (v1 < 0) {
            v1 += length;
        }
        while (v2 < 0) {
            v2 += length;
        }

        int start = Math.min(v1, v2);
        int end = Math.max(v1, v2);

        if (start >= length) {
            start = 0;
        }
        if (end >= length || end == 0) {
            end = length - 1;
        }

        char[] result = new char[end - start + 1]; // 加一表示[右闭]
        System.arraycopy(charArray, start, result, 0, result.length);
        return new SubParseResult(result, start, end);
    }

    @Override
    public String toString() {
        return "Position[" + value1 + ", " + value2 + "]";
    }

}
