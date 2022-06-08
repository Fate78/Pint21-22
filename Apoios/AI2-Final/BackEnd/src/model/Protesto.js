const Sequelize = require('sequelize');
const db = require('./database');

const Protesto = db.define('Protestos', {
    id: {
        type: Sequelize.STRING,
        primaryKey: true,
        autoIncrement: true
    },
    comentario: {
        type: Sequelize.STRING
    },
    createdAt: {
        type: Sequelize.DATE
    },
    updatedAt: {
        type: Sequelize.DATE
    },
    

})

module.exports = Protesto;