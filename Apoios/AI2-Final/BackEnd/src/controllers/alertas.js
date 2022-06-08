const express = require('express');
const router = express.Router();
const db = require('../model/database');
const Zona = require('../model/Zona');
const Denspopulacional = require('../model/Denspopulacional');


const alertas_controller = {};


alertas_controller.list = async (req, res) => {
    const denspop = await Denspopulacional.findAll()
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });

        const zona = await Zona.findAll()
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });
        res.json({ success: true, data: {denspop, zona} });

};


alertas_controller.get = async (req, res) => {
    const { id } = req.params;
    const data = await Zona.findOne({
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



module.exports = alertas_controller;