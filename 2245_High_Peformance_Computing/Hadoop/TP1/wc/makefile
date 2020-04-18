all:
	javac --release 8 wc/*.java
	jar cf wc.jar wc/*
run: all
	yarn jar wc.jar wc.WordCount
clean:
	rm wc/*.class wc.jar