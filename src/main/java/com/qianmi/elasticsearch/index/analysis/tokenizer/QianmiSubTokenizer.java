package com.qianmi.elasticsearch.index.analysis.tokenizer;

import com.qianmi.elasticsearch.index.analysis.common.Position;
import com.qianmi.elasticsearch.index.analysis.common.SubParseResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import java.io.IOException;
import java.util.List;

/**
 * @author hourui 2020/4/30 4:03 PM
 */
public class QianmiSubTokenizer extends Tokenizer {

    private static final Logger LOG = LogManager.getLogger();

    private static final int IO_BUFFER_SIZE = 4096;

    private final List<Position> positions;

    private int sectionOffset = 0;

    private char[] charArray;

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);

    public QianmiSubTokenizer(List<Position> positions) {
        LOG.debug("Init class QianmiSubTokenizer");
        this.positions = positions;
    }

    @Override
    public boolean incrementToken() throws IOException {
        clearAttributes();
        if (null == positions || positions.size() == 0 || sectionOffset >= positions.size()) {
            return false;
        }

        if (sectionOffset == 0) {
            char[] chars = new char[IO_BUFFER_SIZE];
            StringBuilder sb = new StringBuilder();
            while (true) {
                int size = input.read(chars);
                if (size == -1) {
                    break;
                }
                char[] result = new char[size];
                System.arraycopy(chars, 0, result, 0, size);
                sb.append(result);
            }
            charArray = sb.toString().toCharArray();
            if (charArray.length == 0) {
                return false;
            }
            LOG.debug("Read input: {}", sb);
        }
        Position position = positions.get(sectionOffset);
        SubParseResult subParseResult = position.parse(charArray);
        char[] result = subParseResult.getChars();

        LOG.debug("Sub tokenizer: {}, offset: {}, position: {}", new String(result), sectionOffset, position);

        termAtt.copyBuffer(result, 0, result.length);
        termAtt.setLength(result.length);

        offsetAtt.setOffset(sectionOffset, sectionOffset + 1);

        sectionOffset++;
        return true;
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        charArray = null;
        sectionOffset = 0;
    }
}
