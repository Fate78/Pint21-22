const express = require('express');
const router = express.Router();

const classificado_controller = require('../controllers/classificados');

router.post('/create',classificado_controller.create);

router.get('/list', classificado_controller.list);

router.get('/get/:id', classificado_controller.get);

router.post('/update/:id', classificado_controller.update);

router.post('/delete/:id', classificado_controller.delete)

module.exports = router;