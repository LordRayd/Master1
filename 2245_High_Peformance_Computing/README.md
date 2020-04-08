# High Performance Computing for the Big Data
## Enseignants
    Frederick Raiimbault
    Nicolas Courty
## Conrtrole
2 TPs noté avec Raimbault
### Programmation en C 
### Programmation sur Hadoop
#### Upload du fichier par ssh
scp fichier username@ipaddressDistant:DestinationDirectory<br>
scp wc.jar e1604902@cluster-dmis.univ-ubs.fr:TP1_HADOOP

#### Dezippé Jar
unzip nomJar
unzip package.jar

#### Lancé le Jar
hadoop jar fichierJar package.classePrincipale<br>
OU<br>
yarn jar test.jar package.ClassePrincipale

#### Procédure compilation, creation jar et lancement
javac --release 8 -cp $(hadoop classpath) ./package/*.java<br>
jar cvf wc.jar ./package/*.class<br>
yarn jar package.jar package.ClassePrincipale > questionX_sortie.txt