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

        /*
         * 1. 这里需要一个实现了接口AnalysisModule.AnalysisProvider的类的对象；
         * 2. AnalysisModule.AnalysisProvider接口有一个get方法为虚拟方法，实现该接口的类需要实现该方法；
         * 3. 我们并不需要真的去实现一个类并且让该类实现这个get方法，而是可以使用匿名内部类的方式实现；
         *    3.1 当然也可以实现一个类，让该类实现AnalysisModule.AnalysisProvider接口；
         *    3.2 该类也需要实现get方法，get方法的逻辑还是一样的；
         *    3.3 之后在这里创建一个该类的对象即可；
         * 4. 如果使用匿名内部类，则只需要实现get方法即可：
         *    new AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>() {
         *        @Override
         *        public AnalyzerProvider<? extends Analyzer> get(IndexSettings indexSettings, Environment environment, String name, Settings settings) throws IOException {
         *            return new QianmiSubAnalyzerProvider(indexSettings, environment, name, settings);
         *        }
         *    }
         *
         * 5. 由于只需要实现一个get方法，所以可以使用lambda表达式对如上的代码优化如下：
         *    (indexSettings, env, name, settings) -> {
         *        return  new QianmiSubAnalyzerProvider(indexSettings, env, name, settings);
         *    }
         * 6. 由于该方法的方法体只有一行，所以可以把上面的表达式进一步的优化为如下的lambda表达式：
         *    (indexSettings, env, name, settings) -> new QianmiSubAnalyzerProvider(indexSettings, env, name, settings)
         * 7. 由于get方法的参数和后面创建对象的参数一致，所以可以使用lambda表达式进行进一步的优化：
         *    QianmiSubAnalyzerProvider::new
         */
        extra.put("qm_standard", QianmiStandardAnalyzerProvider::new);
        extra.put("sub", QianmiSubAnalyzerProvider::new);

        LOG.info("Set extra {} success", extra);
        return extra;
    }
}
