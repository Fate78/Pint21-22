const Sequelize = require('sequelize');
const db = require('./database');

const Perfil = db.define('Perfils', {
    id: {
        type: Sequelize.STRING,
        primaryKey: true,
        autoIncrement: true
    },
    tipo: {
        type: Sequelize.STRING
    },
    createdAt: {
        type: Sequelize.DATE
    },
    updatedAt: {
        type: Sequelize.DATE
    },

})

module.exports = Perfil;