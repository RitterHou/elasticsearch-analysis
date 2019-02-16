package com.qianmi.analysis.sub;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by liuzhaoming on 2018/7/23.
 */
public class SubStringTokenizer extends Tokenizer {

    private static final int IO_BUFFER_SIZE = 4096;

    private char[] charArray = new char[IO_BUFFER_SIZE];

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

    private final List<Position> positions;

    private int offset = 0;

    private int byteSize = 0;


    /**
     * Construct a token stream processing the given input.
     *
     * @param input 输入流
     */
    public SubStringTokenizer(Reader input) {
        this(input, Collections.singletonList(Position.DEFAULT));
    }

    public SubStringTokenizer(Reader input, List<Position> positionList) {
        super(input);
        this.positions = positionList;
    }

    /**
     * Consumers (i.e., {@link }) use this method to advance the stream to
     * the next token. Implementing classes must implement this method and update
     * the appropriate {@link }s with the attributes of the next
     * token.
     * <p>
     * The producer must make no assumptions about the attributes after the method
     * has been returned: the caller may arbitrarily change it. If the producer
     * needs to preserve the state for subsequent calls, it can use
     * {@link #captureState} to create a copy of the current attribute state.
     * <p>
     * This method is called for every token of a document, so an efficient
     * implementation is crucial for good performance. To avoid calls to
     * {@link #addAttribute(Class)} and {@link #getAttribute(Class)},
     * references to all {@link }s that this stream uses should be
     * retrieved during instantiation.
     * <p>
     * To ensure that filters and consumers know which attributes are available,
     * the attributes must be added during instantiation. Filters and consumers
     * are not required to check for availability of attributes in
     *
     * @return false for end of stream; true otherwise
     */
    @Override
    public boolean incrementToken() throws IOException {
        clearAttributes();
        if (null == positions || positions.size() == 0 || offset >= positions.size()) {
            return false;
        }

        if (offset == 0) {
            byteSize = input.read(charArray);
            if (byteSize <= 0) {
                return false;
            }
        }
        Position position = positions.get(offset);
        int validSize = Math.min(byteSize, position.getSize());
        char[] buffer = termAtt.buffer();
        if (Position.DIRECTION_PRE.equals(position.getDirection())) {
            System.arraycopy(charArray, 0, buffer, 0, validSize);
        } else {
            int startPos = byteSize - validSize;
            System.arraycopy(charArray, startPos, buffer, 0, validSize);
        }

        termAtt.setLength(validSize);
        offset++;
        return true;
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        if (byteSize > 0) {
            Arrays.fill(charArray, 0, byteSize, '\u0000');
        }
        offset = 0;
        byteSize = 0;
    }
}
