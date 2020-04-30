package com.qianmi.elasticsearch.index.analysis.tokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Tokenizer;

import java.io.IOException;

/**
 * @author hourui 2020/4/30 4:03 PM
 */
public class QianmiSubTokenizer extends Tokenizer {

    private static final Logger LOG = LogManager.getLogger();

    public QianmiSubTokenizer() {
        LOG.info("Init class QianmiSubTokenizer");
    }

    @Override
    public boolean incrementToken() throws IOException {
        return false;
    }

}
