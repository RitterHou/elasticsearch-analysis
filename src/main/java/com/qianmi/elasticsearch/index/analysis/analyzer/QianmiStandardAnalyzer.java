package com.qianmi.elasticsearch.index.analysis.analyzer;

import com.qianmi.elasticsearch.index.analysis.tokenizer.QianmiStandardTokenizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

/**
 * @author hourui 2020/4/30 3:58 PM
 */
public class QianmiStandardAnalyzer extends Analyzer {

    private static final Logger LOG = LogManager.getLogger();

    public QianmiStandardAnalyzer() {
        LOG.debug("Init class QianmiStandardAnalyzer");
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new QianmiStandardTokenizer();
        return new TokenStreamComponents(tokenizer);
    }

}
