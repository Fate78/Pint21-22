const express = require('express');
const router = express.Router();

const perfil_controller = require('../controllers/perfis');

router.post('/create',perfil_controller.create);

router.get('/list', perfil_controller.list);

router.get('/get/:id', perfil_controller.get);

router.post('/update/:id', perfil_controller.update);

router.post('/delete/:id', perfil_controller.delete)

module.exports = router;