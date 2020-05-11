# TP 10 - Utilisation MongoDB et serveur Express
## Installation
    Il faut dans un premier temps exécuté les commandes de la Question 1 vu plus bas
    Récupéré les fichiers package.json et server.js ainsi que le dossier views
    Exécuté la ccommande : npm install
## Lancement
    node server.js
    Se rendre à l'URL : http://localhost:3000/home
    Cette url gère le nombre de restaurant et la recherche par latitude longitude
## Question 1
    Installé MongoDB
    Importé base.json dans mongodb
    mongoimport --db mongoimport --collection restaurants --file D:\Cours\Master1\2244_Programmation_Web\TP10\base.json
    La ligne du dessus doit être modifié selon la position du fichier base.json
## Question 3
### Exercice A
#### Enoncé 
    Combien de documents dans la collection ?
#### Réponse
    db.restaurants.count()
    25359
### Exercice B
#### Enoncé 
    Combien de restaurants à Manhattan ?
#### Réponse
    db.restaurants.find({borough : "Manhattan"}).count();
    10259
### Exercice C
#### Enoncé 
    Combien de pizzerias à Manhattan ? 
#### Réponse
    db.restaurants.find({borough : "Manhattan", cuisine : "Pizza"}).count();
    339
### Exercice D
#### Enoncé 
    Combien de restaurants Français dans le Bronx ?
#### Réponse
    db.restaurants.find({borough : "Bronx", cuisine : "French"}).count();
    1
### Exercice E
#### Enoncé 
    Combien de pizzerias à Manhattan qui ont déjà été évalués en catégorie "A" ? 
#### Réponse
    db.restaurants.find({borough : "Manhattan", cuisine : "Pizza", "grades.grade" : "A"}).count();
    327
## Question 7
Oui on a toujours le même nombre de résultats car ils n'ont pas la même adresse