package com.qianmi.elasticsearch.index.analysis.tokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;

/**
 * @author hourui 2020/4/30 4:03 PM
 */
public class QianmiStandardTokenizer extends Tokenizer {

    private static final Logger LOG = LogManager.getLogger();
    // 当前词
    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    // 偏移量
    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);
    // 距离
    private final PositionIncrementAttribute positionAttr = addAttribute(PositionIncrementAttribute.class);
    // 分词词性
    private final TypeAttribute typeAtt = addAttribute(TypeAttribute.class);

    public QianmiStandardTokenizer() {
        LOG.info("Init class QianmiStandardTokenizer");
    }

    @Override
    public boolean incrementToken() throws IOException {
        return false;
    }

}
