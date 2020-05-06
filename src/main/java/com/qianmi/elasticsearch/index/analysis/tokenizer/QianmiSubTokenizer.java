package com.qianmi.elasticsearch.index.analysis.tokenizer;

import com.qianmi.elasticsearch.index.analysis.analyzer.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

/**
 * @author hourui 2020/4/30 4:03 PM
 */
public class QianmiSubTokenizer extends Tokenizer {

    private static final Logger LOG = LogManager.getLogger();

    private static final int IO_BUFFER_SIZE = 4096;

    private char[] charArray = new char[IO_BUFFER_SIZE];

    private int sectionOffset = 0;

    private List<Position> positions;

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

    public QianmiSubTokenizer(Reader reader, List<Position> positions) {
        LOG.info("Init class QianmiSubTokenizer");
        this.input = reader;
        this.positions = positions;
    }

    @Override
    public boolean incrementToken() throws IOException {
        clearAttributes();

        if (null == positions || positions.size() == 0 || sectionOffset >= positions.size()) {
            return false;
        }

        if (sectionOffset == 0) {
            int byteSize = input.read(charArray);
            if (byteSize <= 0) {
                return false;
            }
        }
        Position position = positions.get(sectionOffset);
        char[] result = position.parse(charArray);
        termAtt.copyBuffer(result, 0, result.length);
        termAtt.setLength(result.length);
        sectionOffset++;
        return true;
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        Arrays.fill(charArray, 0, charArray.length, '\u0000');
        sectionOffset = 0;
    }
}
