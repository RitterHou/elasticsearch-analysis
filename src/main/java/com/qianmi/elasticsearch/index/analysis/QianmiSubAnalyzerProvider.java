package com.qianmi.elasticsearch.index.analysis;

import com.qianmi.elasticsearch.index.analysis.analyzer.QianmiSubAnalyzer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

public class QianmiSubAnalyzerProvider extends AbstractIndexAnalyzerProvider<Analyzer> {

    private static final Logger LOG = LogManager.getLogger();

    private final Analyzer analyzer;

    @Inject
    public QianmiSubAnalyzerProvider(IndexSettings indexSettings, Environment env, @Assisted String name, @Assisted Settings settings) {
        super(indexSettings, name, settings);
        LOG.debug(indexSettings);
        LOG.debug(env);
        LOG.debug(name);
        LOG.debug(settings);

        analyzer = new QianmiSubAnalyzer(settings);
    }

    @Override
    public Analyzer get() {
        return analyzer;
    }
}
