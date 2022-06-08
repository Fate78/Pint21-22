const express = require('express');
const router = express.Router();

const login_controller = require('../controllers/login')

router.post('/verificar_login',login_controller.login);

module.exports = router;