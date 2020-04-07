all:
	javac --release 8 top100/*.java
	jar cf top100.jar top100/*
run: all
	yarn jar top100.jar top100.WordCount
clean:
	rm top100/*.class top100.jar