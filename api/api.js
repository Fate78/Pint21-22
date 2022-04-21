var DB = require('./dbOperations');
var Utilizador = require('./utilizador');
const dbOperations = require('./dbOperations');

dbOperations.get_utilizadores().then(result => {
    console.log(result);
})