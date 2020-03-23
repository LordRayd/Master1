const http = require('http');
const URL = require('url');
const FS = require('fs');
const port = 3000;
const request = require('request');
const jsdom = require("jsdom");
const parse = require('url-parse')

const errCb = (err, nomFichier) => {
    let writer = FS.createWriteStream(nomFichier, {
        flags: 'w'
    });

    let reader = FS.createReadStream(err).pipe(writer);
};

const chargement = (url, nomFichier, errCb) => {
    request(url).pipe(FS.createWriteStream(nomFichier)).on('error', (err) => {
        errCb(err, nomFichier);
    });
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
};

const jsdomParser = (url) =>{
    request(url, function (error, response, body) {
        let dom = new jsdom.JSDOM(body);
        dom.window.document.querySelectorAll("img").forEach(function (image,index) {
            console.log(image.getAttribute('src'));
            let urlParse = parse(image.getAttribute('src'), true);
            let extension = image.getAttribute('src').split('.').pop();
            let nom = 'images/' + urlParse.hostname + index + '.' + extension;
            chargement(image.getAttribute('src'), nom, errCb);
            image.setAttribute('src', nom);
        });
        FS.writeFile('test.html', dom.serialize(), err => { // ne marche pas avec les streams
            console.log('done');
        });
    });
}

const recupereImages = (url, prefixe, errCb) => {
    request(url, function (error, response, body) {
        let dom = new jsdom.JSDOM(body);
        dom.window.document.querySelectorAll("img").forEach(function (image,index) {
            console.log(image.getAttribute('src'));
            let extension = image.getAttribute('src').split('.').pop();
            let nom = 'images/' + prefixe + index + '.' + extension;
            chargement(image.getAttribute('src'), nom, errCb);
            image.setAttribute('src', nom);
        });
    });
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
        chargement("https://ljdchost.com/027/revue-code-lol.png", 'images/downloaded.png', errCb);
        reponse.writeHead(200, {'Content-Type': 'text/html'});
        reponse.write("Fichier traité !!");
        reponse.end();
        break;

    case '/jsdom':
        request('http://localhost:3000/index.html', function (error, response, body) {
            const dom = new jsdom.JSDOM(body);
            dom.window.document.querySelectorAll("img").forEach(function (image,index) {
                console.log(image.getAttribute('src'));
                let nom = 'images/image' + index + '.png';
                chargement(image.getAttribute('src'), nom, errCb);
                image.setAttribute('src', nom);

            });
            FS.writeFile('test.html', dom.serialize(), err => { // ne marche pas avec les streams
                console.log('done');
            });
        });
        break;

    case '/jsdomParser' :
        jsdomParser('http://localhost:3000/index.html');
        break;
    case '/recupere' :
        recupereImages('http://localhost:3000/index.html', 'im', errCb);
        break
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