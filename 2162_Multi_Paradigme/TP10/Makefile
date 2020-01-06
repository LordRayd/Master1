all: rm exe
rm : 
	rm *.class
c: 
	scalac Perceptron.scala
exe: c
	scala Main

rmjar:
	rm TP10_Samuel_LEBERRE.jar
jar: rmjar
	scalac Perceptron.scala -d TP10_Samuel_LEBERRE.jar