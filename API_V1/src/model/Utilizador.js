const Sequelize = require('sequelize');
const db = require('./database');

const Utilizador = db.define('Utilizador', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    nome: {
        type: Sequelize.STRING
    },
    sobrenome: {
        type: Sequelize.STRING
    },
    nome_conta: {
        type: Sequelize.STRING
    },
    pass: {
        type: Sequelize.STRING
    },
    data_nascimento: {
        type: Sequelize.STRING
    },
    email: {
        type: Sequelize.STRING
    },
    telemovel: {
        type: Sequelize.STRING
    },
    pontos: {
        type: Sequelize.STRING
    },
})

module.exports = Utilizador;