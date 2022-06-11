const Sequelize = require('sequelize');
const db = require('./database');

const Zona = db.define('Zona', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    descr: {
        type:Sequelize.CHAR
   
    },   
    raio: {
        type:Sequelize.INTEGER
    },
    longitude: {
        type:Sequelize.NUMBER
    },
    latitude: {
        type:Sequelize.NUMBER
    },
    createdAt: {
        type:Sequelize.DATE
    },
    updatedAt: {
        type:Sequelize.DATE
    },

})

module.exports = Zona;