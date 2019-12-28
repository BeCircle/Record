# Note of Information Retrival

## Solr
1. 无法打开"/backup/index/solr/logs/solr.log"， 内存不足引起的。
	注释掉"SOLR_HEAP",将"SOLR_JAVA_MEM"从“-Xms4096m -Xmx4096m”改为“-Xms2048m -Xmx4096m”

