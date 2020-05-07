package com.qianmi.elasticsearch.index.analysis.analyzer;

import com.qianmi.elasticsearch.index.analysis.common.Position;
import com.qianmi.elasticsearch.index.analysis.tokenizer.QianmiSubTokenizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hourui 2020/4/30 3:59 PM
 */
public class QianmiSubAnalyzer extends Analyzer {

    private static final Logger LOG = LogManager.getLogger();

    private List<Position> positions = new ArrayList<>();

    public QianmiSubAnalyzer(Settings settings) {
        LOG.info("Init class QianmiSubAnalyzer");
        String section = settings.get("section");
        if (section == null) {
            section = "0:-1";
            LOG.info("Set section default value: {}", section);
        }
        for (String s : section.split(";")) {
            String[] values = s.split(":");
            if (values.length == 2) {
                try {
                    int value1 = Integer.parseInt(values[0]);
                    int value2 = Integer.parseInt(values[1]);
                    positions.add(new Position(value1, value2));
                } catch (NumberFormatException e) {
                    LOG.error(e);
                }
            }
        }

    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        LOG.info("Create components and positions: {}", positions);
        Tokenizer tokenizer = new QianmiSubTokenizer(positions);
        return new TokenStreamComponents(tokenizer);
    }

}
