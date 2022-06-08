const express = require('express');
const router = express.Router();

const cmv_cont = require('../controllers/cmv/cmvController')

router.post('/verificar_login',cmv_cont.inicio_fim);

module.exports = router;

