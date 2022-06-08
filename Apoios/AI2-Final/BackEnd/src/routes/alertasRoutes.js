const express = require('express');
const router = express.Router();

const alertas_controller = require('../controllers/alertas');


router.get('/list', alertas_controller.list);

router.get('/get/:id', alertas_controller.get);


module.exports = router;