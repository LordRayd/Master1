const express = require('express');
const app = express();
const port = process.env.PORT || 3000;
const MongoClient = require("mongodb").MongoClient;

app.get('*', (req, res, next) => {
	MongoClient.connect("mongodb://localhost/mongoimport", function(error, db) {
        if (error) return funcCallback(error);

        console.log("Connecté à la base de données 'mongoimport'");
    });
});

app.listen(port, function () {
    console.log('Serveur lancer sur le port : ', port);
});