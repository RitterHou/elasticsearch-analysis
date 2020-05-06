package com.qianmi.elasticsearch.plugin;

import com.qianmi.elasticsearch.index.analysis.QianmiStandardAnalyzerProvider;
import com.qianmi.elasticsearch.index.analysis.QianmiSubAnalyzerProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

public class AnalysisQianmiPlugin extends Plugin implements AnalysisPlugin {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> extra = new HashMap<>();

        extra.put("qm_standard", QianmiStandardAnalyzerProvider::new);
        extra.put("sub", QianmiSubAnalyzerProvider::new);

        LOG.info("Set extra {} success", extra);
        return extra;
    }
}
