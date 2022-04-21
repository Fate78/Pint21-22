var config = require('./dbConfig');
const sql = require('mssql');

async function get_utilizadores(){
    try{
        let pool = await sql.connect(config)
        let utilizadores = await pool.request().query("SELECT * from utilizador");
    }
    catch(error){
        console.log(error)
    }
}

module.exports = {
    get_utilizadores : get_utilizadores
}