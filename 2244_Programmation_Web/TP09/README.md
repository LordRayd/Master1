# TP 9 - Création Serveur Express et déploiement sur Heroku
## Authors :
    Samuel LE BERRE
## Pour le déploiement :
    npm install
## Pour le lancement :
    node main.js
## Les coommandes pour le déploiement sur Heroku :
    git init
    git add public/*
    git add views/*
    git add main.js
    git add package-lock.json
    git add package.json
    git add README.md
    git ls-tree --full-tree –r HEAD
    heroku login
    heroku create hmonProjet
    git push heroku –u master
## Pour tester utiliser l'url sur heroku :
    https://tp9progwebsamuel.herokuapp.com

Le serveur se trouve dans le fichier main.js<br>
    Il donne malheureusement accès au js et au css en texte clair si on se dirige vers l'url ce qui crée une sacrée faille des
    sécurité mais le chemin relative ne marche pas. <br>
Le fichier qui gere le css se trouve dans public/css<br>
Le fichier qui gere le js des intéractions utilisateur est dans public/js
