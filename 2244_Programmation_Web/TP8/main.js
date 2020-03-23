const http = require('http');
const URL = require('url');
const FS = require('fs');
const port = 3000;
const request = require('request');
var jsdom = require("node-jsdom");

const errCb = (err, nomFichier) => {
    let writer = FS.createWriteStream(nomFichier, {
        flags: 'w'
    });

    let reader = FS.createReadStream(err).pipe(writer);
}

const chargement = (url, nomFichier, errCb) => {
    request(url).pipe(FS.createWriteStream(nomFichier));
    /*request(url, function (error, response, body) {
        if (response.statusCode == 200) {
            let writer = FS.createWriteStream(nomFichier, {
                flags: 'w'
            });
            writer.write(body);
            //let reader = FS.createReadStream(body).pipe(writer);
        } else {
            errCb(error, nomFichier);
        }
    });*/
}
// callback de réaction
const requestHandler = (requete, reponse) => {
    let ulp = URL.parse(requete.url);
    console.log("requete : " + ulp.pathname);
    switch (ulp.pathname) {
    case '/fichier':
        let writer = FS.createWriteStream('out.txt', {
            flags: 'w'
        });

        let reader = FS.createReadStream('in.txt').pipe(writer);
        reponse.writeHead(200, {'Content-Type': 'text/html'});
        reponse.write("Fichier traité !!");
        reponse.end();
        break;

    case '/requestHTML':
        chargement('http://www.google.com', 'test.html', errCb);
        reponse.writeHead(200, {'Content-Type': 'text/html'});
        reponse.write("Voir le système de fichier");
        reponse.end();
        break;

    case '/requestInFile':
        chargement("https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png", 'downloaded.png', errCb);
        reponse.writeHead(200, {'Content-Type': 'text/html'});
        reponse.write("Fichier traité !!");
        reponse.end();
        break;

    default:
        let rq = ulp.pathname;
        if (rq.length > 1) {
            rq = rq.slice(1);
        } else {
            rq = "index.html";
        }
        FS.readFile(rq, (err, contents) => {

            if (!err) {
                reponse.end(contents);
            } else {
                reponse.writeHead(404, {'Content-Type': 'text/html'});
                reponse.write("Probleme de lecture d'index.html !!");
                reponse.end();
            }
        });
        break;
    }

};

const server = http.createServer(requestHandler);

server.listen(port, (err) => {
    if (err) {
        return console.log('Probleme : ', err);
    }

    console.log(`Le mini serveur est en ligne sur le port : ${port}`);
});