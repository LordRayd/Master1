const pug = require('pug');
const express = require('express');
const app = express();

app.get('/:id_utilisateur', function (req, res) {
    res.send('utilisateur : ' + req.params.id_utilisateur);
});

app.listen(3000, function () {
    console.log('Example app listening on port 3000!');
});