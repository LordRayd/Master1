all:
	javac --release 8 average/*.java
	jar cf average.jar average/*
run: all
	yarn jar average.jar average.Average
clean:
	rm average/*.class average.jar