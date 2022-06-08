const Sequelize = require('sequelize');
const db = require('./database');
const bcrypt = require('bcrypt');

const Utilizador = db.define('Utilizadors', {
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
        type: Sequelize.STRING,
        unique: true
    },
    data_nascimento: {
        type: Sequelize.STRING
    },
    email: {
        type: Sequelize.STRING,
        unique: true
    },
    telemovel: {
        type: Sequelize.STRING
    },
    pontos: {
        type: Sequelize.STRING
    },
    createdAt: {
        type: Sequelize.DATE
    },
    updatedAt: {
        type: Sequelize.DATE
    },
    pass: {
        type: Sequelize.STRING
    },

})

Utilizador.beforeCreate((Utilizadors, options) => {
    return bcrypt.hash(Utilizadors.pass, 10)
        .then(hash => {
            Utilizadors.pass = hash;
        })
        .catch(err => {
            throw new Error();
        });
});

module.exports = Utilizador;