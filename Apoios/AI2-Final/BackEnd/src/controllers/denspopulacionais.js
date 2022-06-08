const express = require('express');
const router = express.Router();
const db = require('../model/database');
const Denspopulacional = require('../model/Denspopulacional');

const denspopulacional_controller = {};

denspopulacional_controller.create = async (req, res) => {
    // data
    const { nota, icon } = req.body;
    let success = true;
    console.log(id);
    // create
    const data = await Denspopulacional.create({
        nota: nota,
        icon: icon
    })
        .then(function (data) {
            return data;
        })
        .catch(error => {
            success = false;
            console.log("Erro: " + error)
            return error;
        })
    console.log(data.id)


    res.status(200).json({
        success: success,
        data: [data1]
    });

}

denspopulacional_controller.list = async (req, res) => {
    const data = await Denspopulacional.findAll()
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });
    res.json({ success: true, data: data });
};


denspopulacional_controller.get = async (req, res) => {
    const { id } = req.params;
    const data = await Denspopulacional.findOne({
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

denspopulacional_controller.update = async (req, res) => {
    const { id } = req.params;
    const { nota, icon} = req.body;
    const data = await Denspopulacional.update({
        nota,
        icon: icon
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

denspopulacional_controller.delete = async (req, res) => {
    const { id } = req.params;
    const data = await Denspopulacional.delete({
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


module.exports = denspopulacional_controller;