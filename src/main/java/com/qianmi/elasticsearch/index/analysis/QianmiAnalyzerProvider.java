package com.qianmi.elasticsearch.index.analysis;

import com.qianmi.elasticsearch.index.analysis.analyzer.QianmiStandardAnalyzer;
import com.qianmi.elasticsearch.index.analysis.analyzer.QianmiSubAnalyzer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

public class QianmiAnalyzerProvider extends AbstractIndexAnalyzerProvider<Analyzer> {

    private static final Logger LOG = LogManager.getLogger();

    private final Analyzer analyzer;

    @Inject
    public QianmiAnalyzerProvider(IndexSettings indexSettings, @Assisted String name, @Assisted Settings settings) {
        super(indexSettings, name, settings);
        LOG.info(indexSettings);
        LOG.info(name);
        LOG.info(settings);

        if ("qm_standard".equals(name)) {
            analyzer = new QianmiStandardAnalyzer();
        } else if ("prefix".equals(name) || "postfix".equals(name)) {
            analyzer = new QianmiSubAnalyzer();
        } else {
            analyzer = new QianmiStandardAnalyzer();
        }
    }

    @Override
    public Analyzer get() {
        return analyzer;
    }
}
