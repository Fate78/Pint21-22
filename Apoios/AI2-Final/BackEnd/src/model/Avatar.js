const Sequelize = require('sequelize');
const db = require('./database');

const Avatar = db.define('Avatars', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    imagem: {
        type: Sequelize.BLOB
    },
    nivel: {
        type: Sequelize.INTEGER
    },
    descr: {
        type: Sequelize.STRING
    },
    createdAt: {
        type: Sequelize.DATE
    },
    updatedAt: {
        type: Sequelize.DATE
    },

})

module.exports = Avatar;