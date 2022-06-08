const Sequelize = require('sequelize');
const db = require('./database');
var Utilizador = require('../model/Utilizador');
var Denspopulacional = require('../model/Utilizador');
var Zona = require('../model/Utilizador');

const Registo = db.define('Registos', {
    id: {
        type: Sequelize.STRING,
        primaryKey: true,
        autoIncrement: true
    },
    nota: Sequelize.STRING,
    
    createdAt: Sequelize.DATE,
    
    foto_local: Sequelize.BLOB,

    gostos: Sequelize.STRING,
    
    updatedAt: Sequelize.DATE,
    
    id_utilizador: {
        type: Sequelize.INTEGER,
        references: {
            model: Utilizador,
            key: 'nome'
        },
        allowNull: false
    },
    id_dens: {
        type: Sequelize.INTEGER,
        references: {
            model: Denspopulacional,
            key: 'id'
        },
        allowNull: false
    },
    id_zona: {
        type: Sequelize.INTEGER,
        references: {
            model: Zona,
            key: 'id'
        },
        allowNull: false
    },
},
    {
        timestamps:false,
    freezeTableName: true
    

});


module.exports = Registo;