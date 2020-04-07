all:
	javac --release 8 computetotal/*.java
	jar cf computetotal.jar computetotal/*
run: all
	yarn jar computetotal.jar computetotal.ComputeTotal
clean:
	rm computetotal/*.class computetotal.jar