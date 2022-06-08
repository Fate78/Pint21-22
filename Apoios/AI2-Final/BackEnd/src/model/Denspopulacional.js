const Sequelize = require('sequelize');
const db = require('./database');

const Denspopulacional = db.define('Denspopulacionals', {
    id: {
        type: Sequelize.STRING,
        primaryKey: true,
        autoIncrement: true
    },
    nota: {
        type: Sequelize.INTEGER
    },
    icon: {
        type: Sequelize.BLOB
    },
    createdAt: {
        type: Sequelize.DATE
    },
    updatedAt: {
        type: Sequelize.DATE
    },

})

module.exports = Denspopulacional;