package com.qianmi.elasticsearch.index.analysis;

import com.qianmi.elasticsearch.index.analysis.analyzer.QianmiStandardAnalyzer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

public class QianmiStandardAnalyzerProvider extends AbstractIndexAnalyzerProvider<Analyzer> {

    private static final Logger LOG = LogManager.getLogger();

    private final Analyzer analyzer;

    @Inject
    public QianmiStandardAnalyzerProvider(IndexSettings indexSettings, Environment env, @Assisted String name, @Assisted Settings settings) {
        super(indexSettings, name, settings);
        LOG.debug(indexSettings);
        LOG.debug(env);
        LOG.debug(name);
        LOG.debug(settings);

        analyzer = new QianmiStandardAnalyzer();
    }

    @Override
    public Analyzer get() {
        return analyzer;
    }
}
