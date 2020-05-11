const express = require('express');
const app = express();
const port = process.env.PORT || 3000;
const MongoClient = require("mongodb").MongoClient;

function radians(nb){
    return nb * ( Math.PI/180);
}

function distance(x1,y1,x2,y2) {
    let radius = 6371;
    return Math.acos(Math.sin(radians(x1)) * Math.sin(radians(x2)) + Math.cos(radians(x1)) * Math.cos(radians(x2)) * Math.cos(radians(y1 - y2))) * radius;
}

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


app.get('/distance', (req, res, next) => {
    res.redirect('/coord/' + req.query.lat + '/' + req.query.long);
});

app.get('/coord/:x/:y', (req, res, next) => {
	MongoClient.connect("mongodb://localhost:27017", {useUnifiedTopology: true} , function(err, client) {
        if (err) {
            res.render('erreurConnection', {error: err});
        } else {
            var db = client.db('mongoimport');
            db.collection('restaurants').find().toArray(function(err, result) {
                let ret = undefined;
                let dist = 2;
                let dist_unit = 'kilomètres';
                result.forEach((item, index) => {
                    if (ret !== undefined){
                        let distcmp = distance(item.address.coord[0], item.address.coord[1], req.params.x, req.params.y);
                        if (dist > distcmp) {
                            ret = item;
                            dist = distcmp;
                        }
                    } else {
                        ret = item;
                        dist = distance(item.address.coord[0], item.address.coord[1], req.params.x, req.params.y);
                    }
                });
                if(dist < 0.01){
                    dist = dist * 1000;
                    dist_unit = 'mètres';
                }
                res.render('restaurant', {restaurant: ret, distance: dist, unit: dist_unit, toDate: (str_) => {
                    let date = new Date(str_);
                    return [date.getDate(), date.getMonth()+1, date.getFullYear()]
                        .map(n => n < 10 ? `0${n}` : `${n}`).join('/');
                }, toNote: (note) => {
                    if(note < 10) return `0${note}`
                    return `${note}`;
                }});
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