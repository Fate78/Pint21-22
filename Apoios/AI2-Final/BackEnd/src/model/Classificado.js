const Sequelize = require('sequelize');
const db = require('./database');

const Classificado = db.define('Classificados', {
    id_registo: {
        type: Sequelize.STRING,
        primaryKey: true,
    },
    id_utilizador: {
        type: Sequelize.STRING,
        primaryKey: true,
        
    },
    avaliacao: {
        type: Sequelize.STRING
    },
    createdAt: {
        type: Sequelize.DATE
    },
    updatedAt: {
        type: Sequelize.DATE
    },

})

module.exports = Classificado;