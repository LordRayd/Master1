all:
	javac --release 8 computeacceptedid/*.java
	jar cf computeacceptedid.jar computeacceptedid/*
run: all
	yarn jar computeacceptedid.jar computeacceptedid.ComputeAcceptedId
clean:
	rm computeacceptedid/*.class computeacceptedid.jar