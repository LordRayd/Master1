all:
	javac --release 8 computetop/*.java
	jar cf computetop.jar computetop/*
run: all
	yarn jar computetop.jar computetop.ComputeTop
clean:
	rm computetop/*.class computetop.jar