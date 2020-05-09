## Qianmi Analyzer for Elasticsearch

打包插件

    mvn clean assembly:assembly
    
查看插件
    
    ➜ ll target/releases
    total 65544
    -rw-r--r--  1 hourui  staff    31M May  9 11:34 elasticsearch-analysis-qianmi-7.5.2-release.zip

安装插件

    ./elasticsearch-7.5.2/bin/elasticsearch-plugin install file:///home/elasticsearch/elasticsearch-analysis-ansj-7.5.2.zip
    