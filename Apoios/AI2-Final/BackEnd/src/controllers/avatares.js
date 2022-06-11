const express = require('express');
const router = express.Router();
const db = require('../model/database');
const Avatar = require('../model/Avatar');

const avatar_controller = {};

avatar_controller.create = async (req, res) => {
    // data
    const { imagem, nivel, descr } = req.body;
    let success = true;
    console.log(id);
    // create
    const data1 = await Avatar.create({
        imagem: imagem,
        nivel: nivel, 
        descr: descr

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

avatar_controller.list = async (req, res) => {
    const data = await Avatar.findAll()
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });
    res.json({ success: true, data: data });
};


avatar_controller.get = async (req, res) => {
    const { id } = req.params;
    const data = await Avatar.findOne({
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

avatar_controller.update = async (req, res) => {
    const { id } = req.params;
    const { imagem, nivel, descr } = req.body;
    const data = await Avatar.update({
        imagem: imagem,
        nivel: nivel, 
        descr: descr
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

avatar_controller.delete = async (req, res) => {
    const { id } = req.params;
    const data = await Avatar.delete({
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


module.exports = avatar_controller;