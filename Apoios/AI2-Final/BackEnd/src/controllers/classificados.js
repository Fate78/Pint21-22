const express = require('express');
const router = express.Router();
const db = require('../model/database');
const Classificado = require('../model/Classificado');

const classificado_controller = {};

classificado_controller.create = async (req, res) => {
    // data
    const { avaliacao } = req.body;
    let success = true;
    console.log(id);
    // create
    const data1 = await Classificado.create({ 
        avaliacao: avaliacao,

    })
        .then(function (data) {
            return data;
        })
        .catch(error => {
            success = false;
            console.log("Erro: " + error)
            return error;
        })
    console.log(data1.id)


    res.status(200).json({
        success: success,
        data: [data1]
    });

}

classificado_controller.list = async (req, res) => {
    const data = await Classificado.findAll()
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });
    res.json({ success: true, data: data });
};


classificado_controller.get = async (req, res) => {
    const { id } = req.params;
    const data = await Classificado.findOne({
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

classificado_controller.update = async (req, res) => {
    const { id } = req.params;
    const { avaliacao } = req.body;
    const data = await Classificado.update({
        avaliacao: avaliacao,

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

classificado_controller.delete = async (req, res) => {
    const { id } = req.params;
    const data = await Classificado.delete({
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


module.exports = classificado_controller;