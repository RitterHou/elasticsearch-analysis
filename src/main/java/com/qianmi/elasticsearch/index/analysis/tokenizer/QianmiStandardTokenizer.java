package com.qianmi.elasticsearch.index.analysis.tokenizer;

import com.qianmi.elasticsearch.index.analysis.common.CharacterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hourui 2020/4/30 4:03 PM
 */
public class QianmiStandardTokenizer extends Tokenizer {

    private static final Logger LOG = LogManager.getLogger();

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);

    private final TypeAttribute typeAtt = addAttribute(TypeAttribute.class);

    private int offset;

    private List<Character> numList = new LinkedList<>();

    private List<Character> wordList = new LinkedList<>();

    private Character character;

    private boolean isEmoji;

    public QianmiStandardTokenizer() {
        LOG.info("Init class QianmiStandardTokenizer");
    }

    @Override
    public boolean incrementToken() throws IOException {
        clearAttributes();

        while (true) {
            char c;
            if (character != null) {
                c = character;
                character = null;
            } else {
                char[] chars = new char[1];
                int count = input.read(chars);
                if (count == -1) {
                    if (numList.size() > 0) {
                        numList.forEach(termAtt::append);
                        offsetAtt.setOffset(offset - numList.size(), offset);
                        typeAtt.setType("number");
                        numList.clear();
                        return true;
                    }
                    if (wordList.size() > 0) {
                        wordList.forEach(termAtt::append);
                        offsetAtt.setOffset(offset - wordList.size(), offset);
                        typeAtt.setType("word");
                        wordList.clear();
                        return true;
                    }
                    return false;
                }
                offset += 1;
                c = chars[0];
            }

            if (isEmoji) {
                isEmoji = false;
                termAtt.append(c); // 写入的emoji的第二个字符
                return true;
            }

            CharacterUtil.CharacterType characterType = CharacterUtil.getCharacterType(c);

            if (numList.size() > 0 && characterType != CharacterUtil.CharacterType.NUM) {
                numList.forEach(termAtt::append);
                offsetAtt.setOffset(offset - numList.size(), offset);
                typeAtt.setType("number");
                numList.clear();
                character = c;
                return true;
            }

            if (wordList.size() > 0 && characterType != CharacterUtil.CharacterType.WORD) {
                wordList.forEach(termAtt::append);
                offsetAtt.setOffset(offset - wordList.size(), offset);
                typeAtt.setType("word");
                wordList.clear();
                character = c;
                return true;
            }

            switch (characterType) {
                case NUM:
                    numList.add(c);
                    break;
                case WORD:
                    wordList.add(c);
                    break;
                case CJK_C:
                case CJK_K:
                case CJK_J:
                    termAtt.append(c);
                    offsetAtt.setOffset(offset - 1, offset);
                    typeAtt.setType("CJK");
                    return true;
                case EMOJI:
                    termAtt.append(c); // 写入emoji的第一个字符
                    offsetAtt.setOffset(offset - 1, offset + 1);
                    typeAtt.setType("EMOJI");
                    isEmoji = true;
                    break;
                default:

            }
        }
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        numList = new LinkedList<>();
        wordList = new LinkedList<>();
    }
}
