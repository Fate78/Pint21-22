const express = require('express');
const router = express.Router();
const db = require('../model/database');
const Protesto = require('../model/Protesto');

const protesto_controller = {};

protesto_controller.create = async (req, res) => {
    // data
    const { comentario } = req.body;
    let success = true;
    console.log(id);
    // create
    const data1 = await Protesto.create({
        comentario: comentario,

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

protesto_controller.list = async (req, res) => {
    const data = await Protesto.findAll()
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });
    res.json({ success: true, data: data });
};


protesto_controller.get = async (req, res) => {
    const { id } = req.params;
    const data = await Protesto.findOne({
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

protesto_controller.update = async (req, res) => {
    const { id } = req.params;
    const { comentario } = req.body;
    const data = await Protesto.update({
        comentario
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

protesto_controller.delete = async (req, res) => {
    const { id } = req.params;
    const data = await Protesto.delete({
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


module.exports = protesto_controller;