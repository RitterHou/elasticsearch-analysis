package com.qianmi.elasticsearch.plugin;

import com.qianmi.elasticsearch.index.analysis.QianmiStandardAnalyzerProvider;
import com.qianmi.elasticsearch.index.analysis.QianmiSubAnalyzerProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

public class AnalysisQianmiPlugin extends Plugin implements AnalysisPlugin, ActionPlugin {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> extra = new HashMap<>();

        extra.put("qm_standard", (indexSettings, env, name, settings) -> new QianmiStandardAnalyzerProvider(indexSettings, name, settings));
        extra.put("sub", (indexSettings, env, name, settings) -> new QianmiSubAnalyzerProvider(indexSettings, name, settings));

        LOG.info("Set extra {} success", extra);
        return extra;
    }
}
