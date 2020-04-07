all:
	javac --release 8 minmaxcount/*.java
	jar cf minmaxcount.jar minmaxcount/*
run: all
	yarn jar minmaxcount.jar minmaxcount.MinMaxCount
clean:
	rm minmaxcount/*.class minmaxcount.jar