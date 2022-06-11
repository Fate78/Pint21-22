const express = require('express');
const router = express.Router();
const db = require('../model/database');
const Utilizador = require('../model/Utilizador');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcrypt');
const { utilizadores } = require('./stats');




const utilizador_controller = {};
db.sync()


utilizador_controller.list = async (req, res) => {
    const data = await Utilizador.findAll()
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });
    res.json({ success: true, data: data });
};


utilizador_controller.get = async (req, res) => {
    const { id } = req.params;
    const data = await Utilizador.findOne({
        where: {
            id: id
        }
    })
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });
    res.json({ success: true, data: data });
};

utilizador_controller.update = async (req, res) => {
    const { id } = req.params;
    const { nome, sobrenome, nome_conta, pass, data_nascimento, email, telemovel, pontos } = req.body;
    const data = await Utilizador.update({
        nome,
        sobrenome,
        nome_conta,
        pass,
        data_nascimento,
        email,
        telemovel,
        pontos
    }, {
        where: {
            id: id
        }
    })
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });
    res.json({ success: true, data: data });
}

utilizador_controller.delete = async (req, res) => {
    const { id } = req.params;
    const data = await Utilizador.delete({
        where: {
            id: id
        }
    })
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });
    res.json({ success: true, data: data });
}





module.exports = utilizador_controller;