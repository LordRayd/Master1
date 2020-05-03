const express = require('express');
const app = express();
const port = process.env.PORT || 3000;
const MongoClient = require("mongodb").MongoClient;

app.set('views', './views');
app.set('view engine', 'pug');

app.get('/home', (req, res, next) => {
	MongoClient.connect("mongodb://localhost:27017", {useUnifiedTopology: true} , function(err, client) {
        if (err) {
            res.render('erreurConnection', {error: err});
        } else {
            var db = client.db('mongoimport');
            db.collection('restaurants').countDocuments(function (e, count) {
                res.render('index', {nombre_restaurant: count});
            });
        }
    });
});

app.get('/noms/:specialite', (req, res, next) => {
	MongoClient.connect("mongodb://localhost:27017", {useUnifiedTopology: true} , function(err, client) {
        if (err) {
            res.render('erreurConnection', {error: err});
        } else {
            var db = client.db('mongoimport');
            db.collection('restaurants').find({cuisine: req.params.specialite.replace('-', ' ')}).sort({borough: 1, name: 1}).toArray(function(err, result) {
                res.render('affichage', {nb: result.length, specialite: req.params.specialite.replace('-', ' '), resultat: result});
            });
        }
    });
});
app.get('/noms/:quartier/:specialite', (req, res, next) => {
	MongoClient.connect("mongodb://localhost:27017", {useUnifiedTopology: true} , function(err, client) {
        if (err) {
            res.render('erreurConnection', {error: err});
        } else {
            var db = client.db('mongoimport');
            db.collection('restaurants').find({borough: req.params.quartier.replace('-', ' '), cuisine: req.params.specialite.replace('-', ' ')}).sort({borough: 1, name: 1}).toArray(function(err, result) {
                res.render('affichage', {nb: result.length, specialite: req.params.specialite.replace('-', ' '), ville: req.params.quartier.replace('-', ' '), resultat: result});
            });
        }
    });
});

app.get('/coord/:y/:y', (req, res, next) => {
	MongoClient.connect("mongodb://localhost:27017", {useUnifiedTopology: true} , function(err, client) {
        if (err) {
            res.render('erreurConnection', {error: err});
        } else {
            var db = client.db('mongoimport');
            db.collection('restaurants').find().toArray(function(err, result) {
                var ret = undefined;
                let dist = 0;
                for (resto in result) {
                    if (ret !== undefined){
                        let distcmp = (resto.address.coord[0] - req.params.x) + (resto.address.coord[1] - req.params.y);
                        if (dist > distcmp) {
                            ret = resto;
                            dist = distcmp;
                        }
                    } else {
                        ret = resto;
                        console.log(resto);
                        dist = (resto.address.coord[0] - req.params.x) + (resto.address.coord[1] - req.params.y);
                    }
                }
                res.render('index', {nombre_restaurant: dist});
            });
        }
    });
}
)
app.get('*', (req, res, next) => {
    res.render('erreur404');
});

app.listen(port, function () {
    console.log('Serveur lancer sur le port : ', port);
});