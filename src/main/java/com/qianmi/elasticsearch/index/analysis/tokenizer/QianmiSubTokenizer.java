package com.qianmi.elasticsearch.index.analysis.tokenizer;

import com.qianmi.elasticsearch.index.analysis.analyzer.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.util.List;

/**
 * @author hourui 2020/4/30 4:03 PM
 */
public class QianmiSubTokenizer extends Tokenizer {

    private static final Logger LOG = LogManager.getLogger();

    private static final int IO_BUFFER_SIZE = 4096;

    private List<Position> positions;

    private int sectionOffset = 0;

    private char[] charArray;

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

    public QianmiSubTokenizer(List<Position> positions) {
        LOG.info("Init class QianmiSubTokenizer");
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
            LOG.info("Read input: {}", sb);
        }
        Position position = positions.get(sectionOffset);
        char[] result = position.parse(charArray);

        LOG.info("Sub tokenizer: {}, offset: {}, position: {}", new String(result), sectionOffset, position);

        termAtt.copyBuffer(result, 0, result.length);
        termAtt.setLength(result.length);
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
