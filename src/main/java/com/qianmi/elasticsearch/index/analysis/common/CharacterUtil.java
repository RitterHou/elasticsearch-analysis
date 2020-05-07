package com.qianmi.elasticsearch.index.analysis.common;

/**
 * @author hourui 2020/5/7 2:35 PM
 */
public class CharacterUtil {

    public enum CharacterType {
        NUM, WORD, CJK_C, CJK_K, CJK_J, UNKNOWN
    }

    /**
     * 获取字符的类型
     *
     * @param c 输入的字符
     * @return 字符的类型
     */
    public static CharacterType getCharacterType(char c) {
        if (c >= '0' && c <= '9') {
            return CharacterType.NUM;
        } else if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
            return CharacterType.WORD;
        } else {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
            if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
                // 中文字符集
                return CharacterType.CJK_C;
            } else if (ub == Character.UnicodeBlock.HANGUL_SYLLABLES
                    || ub == Character.UnicodeBlock.HANGUL_JAMO
                    || ub == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO) {
                // 韩文字符集
                return CharacterType.CJK_K;
            } else if (ub == Character.UnicodeBlock.HIRAGANA // 平假名
                    || ub == Character.UnicodeBlock.KATAKANA // 片假名
                    || ub == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS) {
                // 日文字符集
                return CharacterType.CJK_J;
            }
        }
        return CharacterType.UNKNOWN;
    }

}


