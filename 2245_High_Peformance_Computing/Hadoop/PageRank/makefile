all: jar
	yarn jar pagerank.jar pagerank.ExtractLinksMR
	yarn jar pagerank.jar pagerank.PageRankMR
extract: jar
	yarn jar pagerank.jar pagerank.ExtractLinksMR
pagerank: extract
	yarn jar pagerank.jar pagerank.PageRankMR
jar: compil
	jar cvf pagerank.jar pagerank/*
compil:
	javac --release 8 -cp /share/common/hadoop-3.0.0/etc/hadoop:/share/common/hadoop-3.0.0/share/hadoop/common/lib/*:/share/common/hadoop-3.0.0/share/hadoop/common/*:/share/common/hadoop-3.0.0/share/hadoop/hdfs:/share/common/hadoop-3.0.0/share/hadoop/hdfs/lib/*:/share/common/hadoop-3.0.0/share/hadoop/hdfs/*:/share/common/hadoop-3.0.0/share/hadoop/mapreduce/*:/share/common/hadoop-3.0.0/share/hadoop/yarn:/share/common/hadoop-3.0.0/share/hadoop/yarn/lib/*:/share/common/hadoop-3.0.0/share/hadoop/yarn/* pagerank/*.java
clean:
	rm pagerank/*.class pagerank.jar