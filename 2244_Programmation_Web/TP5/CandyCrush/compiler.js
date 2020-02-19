var NwBuilder = require('nw-builder'); 
var nw = new NwBuilder({   
    files: ['./**'],    
    platforms: ['win64'] //  ['linux64',’linux32’ etc..] les cibles de cross compilation 
}); 
nw.build().then(function () { 
    // promesse Javascript (execute à la fin de la compilation)   
    console.log('Compilation terminée !'); 
}).catch(function (error) {   
    console.error(error); 
}); 