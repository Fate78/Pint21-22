const express = require('express');
const router = express.Router();
const db = require('../model/database');
const Zona = require('../model/Zona');

const zona_controller = {};
db.sync();


zona_controller.create = async (req, res) => {
    // data
    const { descr, raio, longitude, latitude } = req.body;
    // create
    const data = await Zona.create({
        descr: descr, 
        raio: raio, 
        longitude: longitude, 
        latitude: latitude,
    })
        .then(function (data) {
            return data;
        })
        .catch(error => {
            console.log("Erro: " + error)
            return error;
        })
    // return res
    res.status(200).json({
        success: true,
        message: "Registado",
        data: data
    });
}


zona_controller.list = async (req, res) => {
    const data = await Zona.findAll()
        .then(function (data) {
            return data;
        })
        .catch(err => {
            return err;
        });
    res.json({ success: true, data: data });
};


zona_controller.get = async (req, res) => {
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

zona_controller.update = async (req,res) => {
    // parameter get id
    const { id } = req.params;
    // parameter POST
    const {descr, raio, longitude, latitude } = req.body;
    // Update data
    const data = await Zona.update({
        descr: descr, 
        raio: raio, 
        longitude: longitude, 
        latitude: latitude
    },
    {
    where: { id: id}
    })
    .then( function(data){
        return data;
    })
    .catch(error => {
        return error;
    })
    res.json({success:true, data:data, message:"Updated successful"});
}

zona_controller.delete = async (req, res) => {
    // parâmetros por post
    const { id } = req.body;
    // delete por sequelize
    const data = await Zona.destroy({
    where: { id: id}
    })
    res.json({success:true,deleted:data,message:"Deleted successful"});
    }




module.exports = zona_controller;