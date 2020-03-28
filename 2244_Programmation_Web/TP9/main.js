const pug = require('pug');
const path = require('path');
const levenshtein = require('js-levenshtein');
const express = require('express');
const app = express();

var data = [
    "gibbs3456", "toto1234", "test7894", "ennuie5201", "alice0001",
    "bob0002", "long0003", "autretest1234", "encore1256", "dernier6789"
];
var nb_interrogation = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
var months = ["janvier", "fevrier", "mars", "avril", "mai", "juin", "juillet", "aout", "septembre", "octobre", "novembre", "decembre"];

app.get('/:cle/distance', function (req, res) {
    let index = data.indexOf(req.params.cle);
    if (index !== -1) {
        if(req.query.A == undefined || req.query.B == undefined) {
            res.send(JSON.stringify({
                "utilisateur": req.params.cle,
                "erreur": "la requête est mal formée"
            }));
        } else {
            if (req.query.A.length > 50 || req.query.B.length > 50) {
                res.send(JSON.stringify({
                    "utilisateur": req.params.cle,
                    "erreur": "une des deux chaînes est trop longue (gardez des chaînes inférieures à 50)"
                }));
            } else {
                if (nb_interrogation[index] < 5) {
                    if (! /^(A|T|C|G)+$/.test(req.query.A) || ! /^(A|T|C|G)+$/.test(req.query.B) ) {
                        res.send(JSON.stringify({   
                            "utilisateur": req.params.cle,
                            "erreur": "une des chaînes ne code pas de l’ADN"
                        }));
                    } else {
                        let t1 = Date.now();
                        let dist = levenshtein(req.query.A, req.query.B);
                        let diff = Date.now() - t1;
                        let date = new Date(t1);
                        let format = date.getDate() + ' ' + months[date.getMonth() - 1] + ' ' + date.getFullYear() + ' ' + date.getHours() + 'h' + date.getMinutes();
                        nb_interrogation[index] += 1;
                        res.send(JSON.stringify({
                            utilisateur: req.params.cle,
                            date: format,
                            A: req.query.A,
                            B: req.query.B,
                            distance: dist,
                            "temps de calcul (ms)": diff,
                            "interrogations minute": nb_interrogation[index]
                        }));
                    }
                } else {
                    res.send(JSON.stringify({   
                        "utilisateur": req.params.cle,
                        "erreur": "nombre de requêtes dépassé, attendez une minute"
                    }));
                }
            }
        }
    } else {
        res.send(JSON.stringify({   
            "utilisateur": req.params.cle,
            "erreur": "vous n’avez pas les autorisations pour utiliser ce service"
        }));
    }
});

app.get('/', function(req, res) {
    res.sendFile(path.join(__dirname + '/index.html'));
});

app.listen(3000, function () {
    console.log('Example app listening on port 3000!');
});