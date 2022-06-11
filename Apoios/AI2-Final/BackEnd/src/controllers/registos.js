const express = require('express');
const router = express.Router();
const db = require('../model/database');
const Registo = require('../model/Registo');
const Zona = require('../model/Zona');
    

const registo_controller = {};


registo_controller.list = async (req, res) => {
    const data = await Registo.findAll()
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });
    res.json({ success: true, data: data });
};


registo_controller.get = async (req, res) => {
    const { id } = req.params;
    const data = await pessoa.findOne({
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

registo_controller.update = async (req, res) => {
    const { id } = req.params;
    const { nif, cc, nome, p_nome, u_nome, telefone, nascimento, nacionalidade, naturalidade, cod_postal, email, palavra_passe, freg, carta_conducao } = req.body;
    const data = await pessoa.update({
        nif: nif,
        cc: cc,
        nome_completo: nome,
        p_nome: p_nome,
        u_nome: u_nome,
        telefone: telefone,
        data_nascimento: nascimento,
        nacionalidade: nacionalidade,
        naturalidade: naturalidade,
        codigo_postal: cod_postal,
        email: email,
        palavra_passe: palavra_passe,
        freguesia: freg,
        carta_conducao: carta_conducao
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

registo_controller.delete = async (req, res) => {
    const { id } = req.params;
    const data = await pessoa.delete({
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


module.exports = registo_controller;