const express = require('express');
const router = express.Router();
const db = require('../model/database');
const Utilizador = require('../model/Utilizador')
const Sequelize = require('sequelize');
const Classificado = require('../model/Classificado');
const Registo = require('../model/Registo');
const Zona = require('../model/Zona');
const Avatar = require('../model/Avatar');
const Denspopulacional = require('../model/Denspopulacional');
const moment = require('moment'); 
const { and } = require('sequelize');

let date_ob = new Date();

const Op = Sequelize.Op;

const stats = {}

stats.utilizadores = async (req, res) => {
    const data = await Utilizador.findAndCountAll()
        .then(function (data) {
            return data['count'];
        })
        .catch(error => {
            console.log('Erro: ' + error);
            return error;
        });
    res.json({success: true, data: data});

}

stats.avatares = async (req, res) => {
    const data = await Avatar.findAndCountAll()
        .then(function (data) {
            return data['count'];
        })
        .catch(error => {
            console.log('Erro: ' + error);
            return error;
        });
    res.json({success: true, data: data});

}

stats.zonas = async (req, res) => {
    const data = await Zona.findAndCountAll()
        .then(function (data) {
            return data['count'];
        })
        .catch(error => {
            console.log('Erro: ' + error);
            return error;
        });
    res.json({success: true, data: data});

}


stats.reports = async (req, res) => {
    const data = await Classificado.findAndCountAll({ where: {'createdAt': {[Op]: 12-5-2021}}})
        .then(function (data) {
            return data['count'];
        })
        .catch(error => {
            console.log('Erro: ' + error);
            return error;
        });
    res.json({success: true, data: data});

}

stats.registos = async (req, res) => {
    const data = await Registo.findAndCountAll({ include: [{ model: Zona, where: { 'id': { [Op]: 'id_zona'}}}], limit: 3})
        .then(function (data) {
            return data['count'];
        })
        .catch(error => {
            console.log('Erro: ' + error);
            return error;
        });
    res.json({success: true, data: data});

}

stats.urgente = async (req, res) => {
    const data = await Registo.findAll({ where: { nota: "3", 'updatedAt': {[Op.lte]: [moment().subtract(4, 'hours').toDate()]}}  })
    .then(function (data) {
        return data;
    })
    .catch(error => {
        console.log('Erro: ' + error);
        return error;
    });
res.json({success: true, data: data});
}

stats.semana = async (req, res) => {
    const data = await Registo.findAll({ where: { nota: "3", 'updatedAt': {[Op.between]: [moment().subtract(7, 'days').toDate(), date_ob]}} })
    .then(function (data) {
        return data;
    })
    .catch(error => {
        console.log('Erro: ' + error);
        return error;
    });
res.json({success: true, data: data});
}

stats.dia = async (req, res) => {
    const data = await Registo.findAll({ where: { nota: "3", 'updatedAt': {[Op.between]: [moment().subtract(1, 'days').toDate(), date_ob]}}})
    .then(function (data) {
        return data;
    })
    .catch(error => {
        console.log('Erro: ' + error);
        return error;
    });
res.json({success: true, data: data});
}



module.exports = stats;