#Explication du TP

##Upload du fichier par ssh
scp fichier username@ipaddressDistant:DestinationDirectory

scp wc.jar e1604902@cluster-dmis.univ-ubs.fr:TP1_HADOOP

##Dezippé Jar

unzip nomJar

unzip wc.jar

##Lancé le Jar

hadoop jar fichierJar classPrincipale

hadoop jar wc.jar wc.WordCount

##Procédure compilation, creation jar et lancement
javac --release 8 -cp $(hadoop classpath) ./wc/*.java
jar cvf wc.jar ./wc/*.class
hadoop jar wc.jar wc.WordCount > questionX_sortie.txt