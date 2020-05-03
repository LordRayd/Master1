var express = require('express');
var expressMongoRest = require('express-mongo-rest');
var app = express();
app.use('/api/v1', expressMongoRest('mongodb://localhost:27017/mongoimport'));
var server = app.listen(3000, function () {
    console.log('Listening on Port', server.address().port);
});