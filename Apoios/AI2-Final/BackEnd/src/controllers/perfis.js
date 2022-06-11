const express = require('express');
const router = express.Router();
const db = require('../model/database');
const Perfil = require('../model/Perfil');

const perfil_controller = {};

perfil_controller.create = async (req, res) => {
    // data
    const { tipo } = req.body;
    let success = true;
    console.log(id);
    // create
    const data1 = await Perfil.create({
        tipo: tipo

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

perfil_controller.list = async (req, res) => {
    const data = await Perfil.findAll()
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });
    res.json({ success: true, data: data });
};


perfil_controller.get = async (req, res) => {
    const { id } = req.params;
    const data = await Perfil.findOne({
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

perfil_controller.update = async (req, res) => {
    const { id } = req.params;
    const { tipo } = req.body;
    const data = await Perfil.update({
        tipo
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

perfil_controller.delete = async (req, res) => {
    const { id } = req.params;
    const data = await Perfil.delete({
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


module.exports = perfil_controller;