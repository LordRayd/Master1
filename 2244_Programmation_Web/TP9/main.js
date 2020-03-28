const levenshtein = require('js-levenshtein');
const express = require('express');
const app = express();
const port = process.env.PORT || 3000;

var data = [
    "gibbs3456", "toto1234", "test7894", "ennuie5201", "alice0001",
    "bob0002", "long0003", "autretest1234", "encore1256", "dernier6789"
];
var nb_interrogation = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
var months = ["janvier", "fevrier", "mars", "avril", "mai", "juin", "juillet", "aout", "septembre", "octobre", "novembre", "decembre"];
var list_request = [];

const  clear_nb_interrogation = () => {
    nb_interrogation = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
    setTimeout(function(){ clear_nb_interrogation() }, 60000);
}
clear_nb_interrogation();

const clear_list = () =>{
    list_request = [];
};

app.use('/jquery', express.static(__dirname + '/node_modules/jquery/dist/'));
app.use('/js', express.static(__dirname + '/public/js/'));
app.use('/css', express.static(__dirname + '/public/css/'));

app.set('views', './views');
app.set('view engine', 'pug');

app.get('/', (req, res) => {
	res.render('index', {list: list_request});
});

app.get('/clearList', (req, res) => {
    clear_list();
    res.status(200).send(JSON.stringify({
        "resultat": "La liste a bien été vidé"
    }));
});

app.get('/:cle/distance', function (req, res) {
    let index = data.indexOf(req.params.cle);
    if (index !== -1) {
        if (req.query.A === undefined || req.query.B === undefined) {
            res.send();
        } else {
            if (req.query.A.length > 50 || req.query.B.length > 50) {
                res.send(JSON.stringify({
                    "utilisateur": req.params.cle,
                    "erreur": "une des deux chaînes est trop longue (gardez des chaînes inférieures à 50)"
                }));
            } else {
                if (nb_interrogation[index] < 5) {
                    if (!(/^(A|T|C|G)+$/).test(req.query.A) || !(/^(A|T|C|G)+$/).test(req.query.B)) {
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
                        let json_res = JSON.stringify({
                            utilisateur: req.params.cle,
                            date: format,
                            A: req.query.A,
                            B: req.query.B,
                            distance: dist,
                            "temps de calcul (ms)": diff,
                            "interrogations minute": nb_interrogation[index]
                        });
                        list_request.push(json_res);
                        res.send(json_res);
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

app.get('*', (req, res, next) => {
	res.status(200).send('La page que vous rechercher n\'est pas réferencé');
	next();
});

app.listen(port, function () {
    console.log('Serveur lancer sur le port : ', port);
});