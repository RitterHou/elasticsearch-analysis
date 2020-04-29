package org.ansj.test;

import org.ansj.lucene7.AnsjAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ESAnalysisAnsjTests {

    @Test
    public void testAnsj() throws Exception {
        String chineseText = "这是一个elasticsearch的中文分词插件，基于ansj,感谢群内热心的朋友。 并宣传一下我们的群QQ211682609";

        Map<String, String> types = new HashMap<>();
        types.put("type", "base_ansj");

        Analyzer analyzer = new AnsjAnalyzer(types);
        TokenStream tokenStream = analyzer.tokenStream("content", chineseText);
        tokenStream.reset();

        CharTermAttribute cta = tokenStream.getAttribute(CharTermAttribute.class);
        while (tokenStream.incrementToken()) {
            System.out.print(cta.toString() + "|");
        }
        System.out.println();

        tokenStream.end();
        tokenStream.close();
    }

}
