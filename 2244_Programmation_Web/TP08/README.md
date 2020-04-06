# TP 8 - Url parser et modification body
## Autho
    Samuel LE BERRE 23/03/2020

## Pour le deploiement :
    npm install

## Pour lancer le serveur faite la commande :
    node main.js

    
Toutes les questions du TP corresponde chacune à une URL<br>

L'url http://localhost:3000/fichier écrit le contenus du fichier 'in.txt' vers le fichier nouvellement crée 'out.txt'<br>

L'url http://localhost:3000/requestHTML récupère le contenu de la page d'accueil google et le met dans le fichier "test.html"<br>

L'url http://localhost:3000/requestInFile télécharge une image dans le dossier images nommée 'downloaded.png'<br>

L'url http://localhost:3000/jsdom télécharge les images contenues dans le fichier index.html et les renomme avec le nom du site qui les héberge
et change la valeur des src dans le fichier de sortie 'test.html'<br>

L'url http://localhost:3000/jsdomParser recupere une page html et la sauvegarde dans le fichier 'test.html'
en téléchargent dans le dossier images les images qui sont contenues dans la page de base et change les src des balises img.<br>

L'url http://localhost:3000/recupere recupere les images et leur donne un prefixe de im<br>
